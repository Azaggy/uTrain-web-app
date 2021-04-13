package org.liftoff.uTrain.controllers;

import org.liftoff.uTrain.data.UserRepository;
import org.liftoff.uTrain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;
import org.liftoff.uTrain.controllers.AuthenticationController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

AuthenticationController authenticationController;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    String displayIndexPage(Model model) {

        model.addAttribute("title", "Hello World");

        return "index";
    }
}
