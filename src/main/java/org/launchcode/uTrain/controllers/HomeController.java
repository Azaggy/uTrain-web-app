package org.launchcode.uTrain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
