package org.launchcode.uTrain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("exercise")
public class WorkOutController {


    @GetMapping("index")
    public String indexSearch(Model model){
        return("exercise/index");
    }

    @GetMapping("workOut")
    public String trainersDisplay(){
        return("exercise/workOut");
    }

}
