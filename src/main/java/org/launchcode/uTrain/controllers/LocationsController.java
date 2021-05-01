package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.GymRepository;
import org.launchcode.uTrain.data.ParkRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.Gym;
import org.launchcode.uTrain.models.Park;
import org.launchcode.uTrain.models.User;
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

    // Locations controller houses methods for the gym class and activities and also the park class and activities.
    // The plan is to incorporate a geocoder api to be able to inform user/trainer how far facilities are.

    /*
    Like other controllers throughout app, bringing in get user from session method. This allows nagivation of entire
    site to be unique to the user. Also some of the conditionals within the fragments.html file use variables for site
    navigation.
     */

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


    //Link to persisted park class

    @Autowired
    private ParkRepository parkRepository;

    //Link to persisted park class

    @Autowired
    private GymRepository gymRepository;

    //path localhost:8080/gym/addgym
    //populates local variable title with "Add Gym"
    //passes new instance of gym into the view. No attribute is given so, gym will be used. (gym.id, etc.)
    //returns to same path localhost:8080/gym/addgym

    @GetMapping("gym/addgym")
    public String displayAddGym(Model model, HttpServletRequest request){

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("title", "Add Gym");
        model.addAttribute(new Gym());
        return "gym/addgym";
    }

    //path localhost:8080/gym/addgym
    //model binding assigns attributes from submitted form from the view into new instance of gym
    /*
    if there aren't any validations errors, then the new instance is saved to the database and the user is routed back
    to gym/index

    */

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

    //path localhost:8080/park/addpark
    //model binding assigns attributes from submitted form from the view into new instance of park
    /*
    if there aren't any validations errors, then the new instance is saved to the database and the user is routed back
    to park/index
    */

    /*
    unlike the gym class, we're only using zipcode for a geotracking. After the park form field in the view is submitted
    before the instance is saved into the repository, we set the empty fields to unlisted, so they aren't stored as null.
    */

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
