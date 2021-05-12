package org.launchcode.uTrain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    //Controller for display pages with hardly any interaction.

    @GetMapping("index")
    public String hello() {
        return "index";
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




    @GetMapping("photo")
    public String profilePhoto(Model model) {
        model.addAttribute("title", "Profile Photo");

        return "photo";
    }
}
