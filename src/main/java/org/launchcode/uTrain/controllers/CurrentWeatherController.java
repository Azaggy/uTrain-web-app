package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.models.CurrentWeather;
import org.launchcode.uTrain.models.LiveWeatherService;
import org.launchcode.uTrain.models.StubWeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Controller
public class CurrentWeatherController {

//    @GetMapping("/current-weather")
//    public String getCurrentWeather(Model model) {
//        CurrentWeather currentWeather = new CurrentWeather("Clear", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN);
//        model.addAttribute("currentWeather", currentWeather);
//        return "current-weather";
//    }

    private final StubWeatherService stubWeatherService;
    private final LiveWeatherService liveWeatherService;

    public CurrentWeatherController(StubWeatherService stubWeatherService, LiveWeatherService liveWeatherService) {
        this.stubWeatherService = stubWeatherService;
        this.liveWeatherService = liveWeatherService;
    }

    @GetMapping("/current-weather")
    public String getCurrentWeather(Model model) {
        if (true) {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather("Detroit", "us"));
        } else {
            model.addAttribute("currentWeather", stubWeatherService.getCurrentWeather("Detroit", "us"));
        }
        return "current-weather";

    }
}