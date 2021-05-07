package org.launchcode.uTrain.controllers;


import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("photos")
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

    @GetMapping("photo")
    public String avatarPage(HttpServletRequest request, Model model) {

        User user = (User) getUserFromSession(request.getSession());


        model.addAttribute("title", "Photo Selection");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);

        return "photos/photo";
    }
}
