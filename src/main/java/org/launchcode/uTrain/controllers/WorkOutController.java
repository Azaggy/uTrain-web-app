package org.launchcode.uTrain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("exercise")
public class WorkOutController {

    @GetMapping("index")
    public String indexSearch(Model moel){
        return("exercise/index");
    }

    @GetMapping("workOut")
    public String trainersDisplay(){
        return("exercise/workOut");
    }


}
