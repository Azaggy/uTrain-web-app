package org.launchcode.uTrain.controllers;


import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("index")
    public String indexPage(Model model) {
        model.addAttribute("title", "Welcome!!");


        return "index";
    }
}
