package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.MessageRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.Message;
import org.launchcode.uTrain.models.user.User;
import org.launchcode.uTrain.models.user.UserSex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    AuthenticationController authenticationController;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return  user.get();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("index")
    public String userIndexPage(HttpServletRequest request, Model model) {

        User user = (User) getUserFromSession(request.getSession());
        ArrayList<Message> messages = (ArrayList<Message>) messageRepository.findAll();
        ArrayList<Message> sentMessages = new ArrayList<>();
        ArrayList<Message> receivedMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getRecipient().equals(user.getUsername())) {
                receivedMessages.add(message);
            }
            if (message.getSender().equals(user.getUsername())) {
                sentMessages.add(message);
            }
        }

        // Sorting methods to sort messages newest, to oldest.
        Collections.sort(sentMessages, (c1, c2) -> {
            if (c1.getDate().after(c2.getDate())) return -1;
            else return 1;
        });

        // Sorting methods to sort messages newest, to oldest.
        Collections.sort(receivedMessages, (c1, c2) -> {
            if (c1.getDate().after(c2.getDate())) return -1;
            else return 1;
        });

        /*
        User is directed to the user index page after a successful login is completed.
        The variable loggedIn is used to display certain links if user is logged in.
         */

        model.addAttribute("title", "Welcome!!");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("sentMessages", sentMessages);

        return "user/index";

    }

    @GetMapping("addprofile/{userId}")
    public String displayEditProfileForm(Model model, @PathVariable int userId) {

        /*
        User is directed to add profile from link if the boolean isNew is true. If false they'll be
        directed to the profile page.

         */

        Optional<User> result = userRepository.findById(userId);
        User updateUser = result.get();

        model.addAttribute("title", "Update " + updateUser.getUsername() + "'s profile");
        model.addAttribute("user", updateUser);
        model.addAttribute("loggedIn", true);
        model.addAttribute("sexes", UserSex.values());

        return "user/addprofile";
    }

    @PostMapping("addprofile")
    public String processEditProfileForm(@ModelAttribute @Valid User user, Errors errors, Model model) {

        Optional<User> result = userRepository.findById(user.getId());
        User updatedUser = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update " + updatedUser.getUsername() + "'s profile");
            model.addAttribute("user", updatedUser);
            model.addAttribute("loggedIn", true);
            model.addAttribute("userId", user.getId());
            return "user/addprofile";
        }

//        userRepository.deleteById(userId);

        // Once user adds detailed information to profile it sets isNew to false. From there when they select
        // profile it will take them to the profile page.

        user.setNew(false);

        // User info is updated and saved to the database with new information
       userRepository.save(user);


        return "redirect:/user/index";
    }

    @GetMapping("profile")
    public String userProfile(HttpServletRequest request, Model model) {

        // This page is only displayed if the user variable isNew is false.

        User user = (User) getUserFromSession(request.getSession());




        model.addAttribute("title", user.getUserDetail().getFirstName() + "'s Profile");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);

        return "user/profile";
    }

}
