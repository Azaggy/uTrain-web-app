package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.TrainerRepository;
import org.launchcode.uTrain.models.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("trainer")
public class TrainerController {

    @Autowired
    private TrainerRepository trainerRepository;

    @GetMapping
    public String displayAllTrainers(Model model) {
        model.addAttribute("title", "All Trainers");
        model.addAttribute("trainers", trainerRepository.findAll());
        return "trainer/index";
    }

    @GetMapping("create")
    public String displayCreateTrainerForm(Model model){
        model.addAttribute("title", "Create Trainer");
        model.addAttribute(new Trainer());
        return "trainer/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Trainer newTrainer,
                                         Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Trainer");
            return "trainer/create";
        }
        trainerRepository.save(newTrainer);
        return "redirect:";
    }

}
