package org.launchcode.uTrain.controllers;


import org.launchcode.uTrain.FileUploadUtil;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.User;
import org.launchcode.uTrain.models.UserPhoto;
import org.launchcode.uTrain.models.UserSex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class PhotoController {

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

    @PostMapping("user/profilePhoto")
    public RedirectView saveUserPhoto(User user,
                                      @RequestParam("image")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.getUserPhoto().setProfilePic(fileName);

        User savedUser = userRepository.save(user);

        String uploadDir = "user-photos/" + savedUser.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/user/profilePhoto", true);
    }

    @GetMapping("profilePhoto")
    public String avatarPage(HttpServletRequest request, Model model) {

        User user = (User) getUserFromSession(request.getSession());


        model.addAttribute("title", "Photo Selection");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);


        return "user/profilePhoto";
    }


    @GetMapping("profilePhoto/{userId}")
    public String displayEditProfilePhotoForm(Model model, @PathVariable int userId) {

        /*
        User is directed to add profile from link if the boolean isNew is true. If false they'll be
        directed to the profile page.

         */

        Optional<User> result = userRepository.findById(userId);
        User updateUser = result.get();

        model.addAttribute("title", "Update " + updateUser.getUsername());
        model.addAttribute("user", updateUser);
        model.addAttribute("loggedIn", true);
        model.addAttribute("sexes", UserSex.values());
        model.addAttribute("profilePic", updateUser.getUserPhoto());

        return "user/profilePhoto";
    }

    @PostMapping("profilePhoto")
    public String processEditProfilePhotoForm(@ModelAttribute @Valid User user, int userId,
                                         Errors errors, Model model) {

        Optional<User> result = userRepository.findById(userId);
        User updatedUser = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update " + updatedUser.getUsername());
            model.addAttribute("user", updatedUser);
            model.addAttribute("loggedIn", true);
            model.addAttribute("userId", userId);

            return "user/profilePhoto";
        }
        model.addAttribute("profilePic", user.getUserPhoto());

//        userRepository.deleteById(userId);

        // Once user adds detailed information to profile it sets isNew to false. From there when they select
        // profile it will take them to the profile page.

        // User info is updated and saved to the database with new information
        userRepository.save(user);

        return "redirect:/user/profile";
    }
}
