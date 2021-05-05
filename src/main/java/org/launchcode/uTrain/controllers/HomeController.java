package org.launchcode.uTrain.controllers;


import org.launchcode.uTrain.data.GymRepository;
import org.launchcode.uTrain.data.ParkRepository;
import org.launchcode.uTrain.models.Gym;
import org.launchcode.uTrain.models.Park;
import org.launchcode.uTrain.models.User;
import org.launchcode.uTrain.models.UserSex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeController {

    //Controller for display pages with hardly any interaction.
    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private GymRepository gymRepository;

    @GetMapping("index")
    public String hello() {
        return "index";
    }

    @GetMapping("homeExercises")
    public String homeExercises() {
        return "homeExercises";
    }

    @GetMapping("company")
    public String aboutPage(Model model) {
        model.addAttribute("title", "About Us");

        return "company";
    }

}
