package org.launchcode.uTrain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.LiveWeatherService;
import org.launchcode.uTrain.models.StubWeatherService;
import org.launchcode.uTrain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
public class CurrentWeatherController {
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
        return user.get();
    }

    @Autowired
    UserRepository userRepository;

    private final StubWeatherService stubWeatherService;
    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model, HttpServletRequest request) throws JsonProcessingException {
        User user = (User) getUserFromSession(request.getSession());
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("title", "Weather");


        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather2", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather2", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        return "current-weather";

    }

    @GetMapping("/maps")
    public String getMaps(Model model, HttpServletRequest request) {
        User user = (User) getUserFromSession(request.getSession());
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);

        return "maps";
    }

}