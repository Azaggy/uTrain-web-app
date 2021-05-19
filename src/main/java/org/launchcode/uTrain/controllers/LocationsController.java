package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.GymRepository;
import org.launchcode.uTrain.data.ParkRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.Gym;
import org.launchcode.uTrain.models.Park;
import org.launchcode.uTrain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LocationsController {

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
    private ParkRepository parkRepository;

    @Autowired
    private GymRepository gymRepository;

    @GetMapping("gym/addgym")
    public String displayAddGym(Model model, HttpServletRequest request){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("title", "Add Gym");
        model.addAttribute(new Gym());
        return "gym/addgym";
    }

    @PostMapping("gym/addgym")
    public String processAddGym(@ModelAttribute @Valid Gym newGym, Errors errors, Model model){

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Gym");
            return "gym/addgym";
        }

        gymRepository.save(newGym);
        return "redirect:index";
    }

    @GetMapping("gym/index")
    public String gymIndex(Model model, HttpServletRequest request){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("gyms", gymRepository.findAll());
        model.addAttribute("title", "Gym List");

        return "gym/index";
    }

    @GetMapping("park/addpark")
    public String displayAddPark(Model model, HttpServletRequest request){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("title", "Add Park");
        model.addAttribute(new Park());

        return "park/addpark";
    }

    @PostMapping("park/addpark")
    public String processAddPark(@ModelAttribute @Valid Park newPark, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Park");
            return "park/addpark";
        }

        newPark.getAddress().setCity("unlisted");
        newPark.getAddress().setState("unlisted");
        newPark.getAddress().setStreet("unlisted");
        parkRepository.save(newPark);

        return "redirect:index";
    }

    @GetMapping("park/index")
    public String parkIndex(Model model, HttpServletRequest request){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("parks", parkRepository.findAll());
        model.addAttribute("title", "Park List");
        return "park/index";
    }
}
