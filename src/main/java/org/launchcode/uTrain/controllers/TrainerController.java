package org.launchcode.uTrain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.launchcode.uTrain.data.TrainerRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.LiveWeatherService;
import org.launchcode.uTrain.models.Trainer;
import org.launchcode.uTrain.models.user.User;
import org.launchcode.uTrain.models.workout.ExerciseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("trainer")
public class TrainerController {



    private static final String userSessionKey = "user";

    public TrainerController(LiveWeatherService liveWeatherService) {
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
    private TrainerRepository trainerRepository;

    private final LiveWeatherService liveWeatherService;

    @GetMapping("index")
    public String displayAllTrainers(HttpServletRequest request, Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "All Trainers");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("trainers", trainerRepository.findAll());
        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }
        return "trainer/index";
    }

    @GetMapping("create")
    public String displayCreateTrainerForm(HttpServletRequest request, Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "Create Trainer");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute(new Trainer());
        model.addAttribute("types", ExerciseType.values());
        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }
        return "trainer/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Trainer newTrainer,
                                         Errors errors, Model model, HttpServletRequest request) throws JsonProcessingException {
        User user = (User) getUserFromSession(request.getSession());
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Trainer");
//            model.addAttribute("user", user);
            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
            return "trainer/create";
        }
        trainerRepository.save(newTrainer);
        return "redirect:/trainer/index";
    }

    @GetMapping("edit/{trainerId}")
    public String displayEditForm(HttpServletRequest request, Model model, @PathVariable int trainerId) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        Optional<Trainer> result = trainerRepository.findById(trainerId);
        Trainer trainer = result.get();

        model.addAttribute("title", "Edit Trainer" + trainer.getName() + " (id=" + trainer.getId() + ")");
        model.addAttribute("trainer", trainer);
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("types", ExerciseType.values());
        model.addAttribute("trainerId", trainer.getId());
        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        return"trainer/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@ModelAttribute @Valid Trainer trainer, Errors errors, Model model,
                                  HttpServletRequest request) throws JsonProcessingException {
        User user = (User) getUserFromSession(request.getSession());
        Optional<Trainer> result = trainerRepository.findById(trainer.getId());
        Trainer newTrainer = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Trainer" + trainer.getName() + " (id=" + trainer.getId() + ")");
            model.addAttribute("trainer", newTrainer);
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("types", ExerciseType.values());
            model.addAttribute("trainerId", trainer.getId());
            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }

            return "trainer/edit";

        }
        trainerRepository.save(trainer);

        return "redirect:/trainer/index";
    }
}
