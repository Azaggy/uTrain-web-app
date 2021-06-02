package org.launchcode.uTrain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.launchcode.uTrain.data.ExerciseRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.data.WorkoutRepository;
import org.launchcode.uTrain.models.LiveWeatherService;
import org.launchcode.uTrain.models.user.User;
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

    public WorkOutController(LiveWeatherService liveWeatherService) {
        this.liveWeatherService = liveWeatherService;
    }

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

    private final LiveWeatherService liveWeatherService;


    @GetMapping("workouts")
    public String displayWorkouts(HttpServletRequest request, Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        /*
        The workout class is in a ManyToOne relationship to the user. Once the user is obtained from the
        session, the workouts associated with the user can be obtained.
         */
        List<Workout> workouts = user.getWorkouts();

        // Sort method to list the workouts from more recent to oldest.
        Collections.sort(workouts, (c1, c2) -> {
            if (c1.getTimeStamp().after(c2.getTimeStamp())) return -1;
            else return 1;
        });

        model.addAttribute("title", "Workouts");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("workouts", workouts);
        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        return"workout/workouts";
    }

    @GetMapping("addworkout")
    public String addWorkouts(HttpServletRequest request, Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "Add Workout");
        model.addAttribute("user", user);
        model.addAttribute("types", ExerciseType.values());
        model.addAttribute("loggedIn", true);
        model.addAttribute("workout", new Workout());
        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        return "workout/addworkout";
    }

    @PostMapping("addworkout")
    public String renderAddWorkouts(@ModelAttribute @Valid Workout workout, HttpServletRequest request, Errors errors,
                                    Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        //grabbing the current date to add to the workout before it's saved to the repository
        Date currentDate = Calendar.getInstance().getTime();
        Date dateInSec = user.truncToSec(currentDate);

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Workout");
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("workout", new Workout());

            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }

            return "workout/addworkout";
        }

//        if (user.getUserDetail().getUserSex().getUserSex() == "Female") {
//
//            double burnedCals = workout.getCaloriesBurnedForFemale(user.getUserDetail().getWeight(),
//                    user.getUserDetail().getAge());
//            workout.setBurnedCal(Math.round(burnedCals));
//        } else if (user.getUserDetail().getUserSex().getUserSex() == "Male") {
//            double burnedCals = workout.getCaloriesBurnedForMale(user.getUserDetail().getWeight(),
//                    user.getUserDetail().getAge());
//            workout.setBurnedCal(Math.round(burnedCals));
//        }

        /*Used a MET calc which goes off of type of exercise. Incorporated a switch statement that returns
        numbers based on exercise type.
        */
        if (user.getUserDetail().getUserSex().getUserSex() != null) {

            double burnedCals = workout.getCaloriesBurnedUsingMet(user.getUserDetail().getWeight(),
                    workout.metGetter(workout.getExercise().getExerciseType().getExerciseType()));
            workout.setBurnedCal(Math.round(burnedCals));
        }



        //we set the date and the user to the workout. The User's id will be stored on the workout table
        workout.setTimeStamp(dateInSec);
        workout.setUser(user);
        workoutRepository.save(workout);

        return "redirect:workouts";

    }

    @GetMapping("instructionalVideos")
    public String presentInstructionalVideos (HttpServletRequest request, Model model) throws JsonProcessingException {
        User user = (User) getUserFromSession(request.getSession());

        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        model.addAttribute("title", "Instructional Videos");
        model.addAttribute("user", user);
        return "workout/instructionalVideos";
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
