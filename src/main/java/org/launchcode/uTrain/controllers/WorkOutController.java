package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.ExerciseRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.data.WorkoutRepository;
import org.launchcode.uTrain.models.user.User;
import org.launchcode.uTrain.models.user.UserSex;
import org.launchcode.uTrain.models.workout.Exercise;
import org.launchcode.uTrain.models.workout.ExerciseType;
import org.launchcode.uTrain.models.workout.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("workout")
public class WorkOutController {

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return  user.get();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    WorkoutRepository workoutRepository;


    @GetMapping("workouts")
    public String displayWorkouts(HttpServletRequest request, Model model){

        User user = (User) getUserFromSession(request.getSession());

        List<Workout> workouts = user.getWorkouts();

        Collections.sort(workouts, (c1, c2) -> {
            if (c1.getTimeStamp().after(c2.getTimeStamp())) return -1;
            else return 1;
        });

        model.addAttribute("title", "Workouts");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("workouts", workouts);

        return"workout/workouts";
    }

    @GetMapping("addworkout")
    public String addWorkouts(HttpServletRequest request, Model model){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "Add Workout");
        model.addAttribute("user", user);
        model.addAttribute("types", ExerciseType.values());
        model.addAttribute("loggedIn", true);
        model.addAttribute("workout", new Workout());

        return "workout/addworkout";
    }

    @PostMapping("addworkout")
    public String renderAddWorkouts(@ModelAttribute @Valid Workout workout, HttpServletRequest request, Errors errors,
                                    Model model) {

        User user = (User) getUserFromSession(request.getSession());
        Date currentDate = Calendar.getInstance().getTime();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Workout");
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("workout", new Workout());

            return "workout/addworkout";
        }

        workout.setTimeStamp(currentDate);
        workout.setUser(user);
        workoutRepository.save(workout);

        return "redirect:workouts";

    }

//    @GetMapping("activities")
//    public String displayCurrentActivities(HttpServletRequest request, Model model) {
//
//        User user = (User) getUserFromSession(request.getSession());
//
//        model.addAttribute("title", user.getUsername() + "'s Activities");
//        model.addAttribute("exercises", user.getExercises());
//        model.addAttribute("user", user);
//        model.addAttribute("loggedIn", true);
//
//        return ("workout/activities");
//    }
//
//    @GetMapping("addactivities")
//    public String displayAddActivity(HttpServletRequest request, Model model) {
//
//        User user = (User) getUserFromSession(request.getSession());
//
//        model.addAttribute("title", "Add Activity");
//        model.addAttribute("loggedIn", true);
//        model.addAttribute("types", ExerciseType.values());
//        model.addAttribute("user", user);
//        model.addAttribute("exercise", new Exercise());
//
//        return "workout/addactivities";
//    }
//
//    @PostMapping("addactivities")
//    public String renderAddActivity(@ModelAttribute @Valid Exercise exercise, HttpServletRequest request, Errors errors, Model model) {
//
//        User user = (User) getUserFromSession(request.getSession());
//
//        if (errors.hasErrors()) {
//            model.addAttribute("title", "Add Activity");
//            model.addAttribute("user", user);
//            model.addAttribute("loggedIn", true);
//            model.addAttribute("types", ExerciseType.values());
//            return "workout/addactivities";
//        }
//
//        exercise.setUser(user);
//
//        exerciseRepository.save(exercise);
//
//        return "redirect:/workout/activities";
//    }


}
