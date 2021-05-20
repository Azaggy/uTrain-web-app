package org.launchcode.uTrain.controllers;


import org.launchcode.uTrain.AuthenticationFilter;
import org.launchcode.uTrain.data.BackgroundImage;
import org.launchcode.uTrain.data.GymRepository;
import org.launchcode.uTrain.data.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    //Controller for display pages with hardly any interaction.
    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private GymRepository gymRepository;

    AuthenticationFilter filter;

    @GetMapping("index")
    public String indexPage(Model model) {
        BackgroundImage image = new BackgroundImage();

        model.addAttribute("title", "Welcome To uTrain!!");
        model.addAttribute("backgroundImage", image.randomImageGenerator());

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

    @GetMapping("bmiLoggedOut")
    public String bmiCalc(Model model) {

        model.addAttribute("title", "BMI Calculator");
        return "bmiLoggedOut";
    }



//
}
