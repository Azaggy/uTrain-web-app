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
    @GetMapping("gym/addGym")
    public String addGym(Model model){
        model.addAttribute("title", "Add Gym");
        model.addAttribute(new Gym());
        return "gym/addGym";
    }

    @PostMapping
    public String processAddGym(){

        return "gym/listGyms";
    }




    @GetMapping("gym/listGyms")
    public String listGym(Model model){
        model.addAttribute("title", "Gym List");
        return "gym/listGyms";
    }

    @GetMapping("park/addPark")
    public String addPark(Model model){

        model.addAttribute("title", "Add Park");
        model.addAttribute(new Park());

        return "park/addPark";
    }

    @PostMapping("park/addPark")
    public String processAddPark(@ModelAttribute @Valid Park newPark, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Park");
        return "park/addPark";
    }
        parkRepository.save(newPark);
        return "/park/listParks";
    }


    @GetMapping("park/listParks")
    public String listPark(Model model){
        model.addAttribute("title", "Park List");
        model.addAttribute("Parks", parkRepository.findAll());
        return "park/listParks";
    }
}
