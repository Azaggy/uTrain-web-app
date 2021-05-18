package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.BackgroundImage;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.user.User;
import org.launchcode.uTrain.models.dto.LoginFormDTO;
import org.launchcode.uTrain.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

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

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/reg")
    public String displayRegistrationForm(Model model) {

        BackgroundImage image = new BackgroundImage();

        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register for uTrain");
        model.addAttribute("backgroundImage", image.randomImageGenerator());
        return "reg";
    }

    @PostMapping("/reg")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, Model model) {
        BackgroundImage image = new BackgroundImage();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register for uTrain");
            model.addAttribute("backgroundImage", image.randomImageGenerator());
            return "reg";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already " +
                    "exists");
            model.addAttribute("title", "Register for uTrain");
            return "reg";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();

        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match!");
            model.addAttribute("title", "Register for uTrain");
            return "reg";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword(),
                registerFormDTO.getEmail());

        userRepository.save(newUser);

        return "redirect:index";
    }

    @GetMapping("/login")
    public  String displayLoginForm(Model model) {
        BackgroundImage image = new BackgroundImage();

        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        model.addAttribute("backgroundImage", image.randomImageGenerator());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO, Errors errors,
                                   HttpServletRequest request, Model model) {
        BackgroundImage image = new BackgroundImage();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            model.addAttribute("backgroundImage", image.randomImageGenerator());
            return "login";
            

        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if(theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password!");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        User user = (User) getUserFromSession(request.getSession());
        model.addAttribute("user", user);

        return "redirect:/user/index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }
}
