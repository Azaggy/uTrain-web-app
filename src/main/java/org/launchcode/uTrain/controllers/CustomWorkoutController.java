package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.WorkoutData;
import org.launchcode.uTrain.models.workout.CustomWorkout;
import org.launchcode.uTrain.models.workout.Workout;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("customworkout")
public class CustomWorkoutController {


    @GetMapping("index")
    public String displayAllExercises(Model model) {
        model.addAttribute("customWorkouts", WorkoutData.getAll());
        return "customworkout/index";
    }

    @GetMapping("create")
    public String renderCreateWorkoutForm() {
        return "customworkout/create";
    }

    @PostMapping("create")
    public String createWorkout(@ModelAttribute Workout newWorkout,
                                Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Customize Your Workout");
            return "customworkout/create";
        }
        WorkoutData.add(newWorkout);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteWorkoutForm(Model model) {
        model.addAttribute("title", "Delete Workouts");
        model.addAttribute("customWorkouts", WorkoutData.getAll());
        return "customworkout/delete";
    }

    @PostMapping("delete")
    public String processDeleteWorkoutForm(@RequestParam(required = false) int[] workoutIds) {

        if (workoutIds != null) {
            for (int id : workoutIds) {
                WorkoutData.remove(id);
            }
        }
        return "redirect:";
    }
}


