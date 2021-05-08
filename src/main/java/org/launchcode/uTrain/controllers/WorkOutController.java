package org.launchcode.uTrain.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("exercise")
public class WorkOutController {

    private static List<String> customWorkouts = new ArrayList<>();

    @GetMapping("index")
    public String indexSearch(Model model){
        model.addAttribute("customWorkouts", customWorkouts);
        return("exercise/index");
    }

    @GetMapping("workOut")
    public String trainersDisplay(){
        return("exercise/workOut");
    }

    @PostMapping("index")
    public String createWorkOutForm(@RequestParam String workOutName) {
        customWorkouts.add(workOutName);
        return "exercise/index";
    }


}
