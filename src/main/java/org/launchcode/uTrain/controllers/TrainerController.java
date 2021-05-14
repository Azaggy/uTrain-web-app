package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.TrainerRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.Trainer;
import org.launchcode.uTrain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("trainer")
public class TrainerController {

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
    private TrainerRepository trainerRepository;

    @GetMapping
    public String displayAllTrainers(HttpServletRequest request, Model model) {

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "All Trainers");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("trainers", trainerRepository.findAll());
        return "trainer/index";
    }

    @GetMapping("create")
    public String displayCreateTrainerForm(HttpServletRequest request, Model model){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "Create Trainer");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute(new Trainer());
        return "trainer/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Trainer newTrainer,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Trainer");
            return "trainer/create";
        }
        trainerRepository.save(newTrainer);
        return "redirect:";
    }

}
