package org.launchcode.uTrain.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("")
    public String hello() {
        return "index";
    }

    @GetMapping("/register")
    public String displayRegisterForm(Model model){
        model.addAttribute("title", "Register");
        return "register";
    }
}
