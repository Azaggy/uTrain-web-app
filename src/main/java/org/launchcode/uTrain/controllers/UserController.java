package org.launchcode.uTrain.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.launchcode.uTrain.data.*;
import org.launchcode.uTrain.models.*;
import org.launchcode.uTrain.models.user.UserPhoto;
import org.launchcode.uTrain.models.friend.Friend;
import org.launchcode.uTrain.models.user.User;
import org.launchcode.uTrain.models.user.UserSex;
import org.launchcode.uTrain.models.workout.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    AuthenticationController authenticationController;

    private static final String userSessionKey = "user";

    public UserController(LiveWeatherService liveWeatherService) {
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
    MessageRepository messageRepository;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    GymRepository gymRepository;

    private final LiveWeatherService liveWeatherService;

    @GetMapping("index")
    public String userIndexPage(HttpServletRequest request, Model model, UserPhoto userPhoto) throws JsonProcessingException {




        BackgroundImage image = new BackgroundImage();

        //Pulling user from session
        User user = (User) getUserFromSession(request.getSession());

//        CurrentWeather currentWeather = liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us");
//        CurrentWeather

        //Initiating Lists for loading message data onto user's index page
        ArrayList<Message> messages = (ArrayList<Message>) messageRepository.findAll();
        ArrayList<Message> sentMessages = new ArrayList<>();
        ArrayList<Message> receivedMessages = new ArrayList<>();

        //Initiating Lists for loading shared workout data onto user's index page
        ArrayList<Workout> sharedWorkouts = new ArrayList<>();

        //Initiating Lists for loading user's workouts onto user's index page
        ArrayList<Workout> userWorkouts = new ArrayList<>();

        //Initiating Lists for loading park data onto user's index page
        ArrayList<Park> parks= (ArrayList<Park>)parkRepository.findAll();
        ArrayList<Park> matchingParks = new ArrayList<>();

        //Initiating Lists for loading gym data onto user's index page
        ArrayList<Gym> gyms= (ArrayList<Gym>)gymRepository.findAll();
        ArrayList<Gym> matchingGyms= new ArrayList<>();

        //Looping through messages to display onto page. Take messages and divides them by sent or receive
        for (Message message : messages) {
            if (message.getRecipient().equals(user.getUsername())) {
                receivedMessages.add(message);
            }
            if (message.getSender().equals(user.getUsername())) {
                sentMessages.add(message);
            }
        }

        // Sorting methods to sort messages newest, to oldest.
        Collections.sort(sentMessages, (c1, c2) -> {
            if (c1.getDate().after(c2.getDate())) return -1;
            else return 1;
        });

        // Sorting methods to sort messages newest, to oldest.
        Collections.sort(receivedMessages, (c1, c2) -> {
            if (c1.getDate().after(c2.getDate())) return -1;
            else return 1;
        });

        //Looping through parks to display onto page. Finds parks that match user's zip code and add to list.
        if(user.getUserDetail() != null) {
            for (Park park : parks) {
                if (park.getAddress().getZipCode() == user.getUserDetail().getAddress().getZipCode()) {
                    matchingParks.add(park);
                } 
            }
        }

        //Looping through gyms to display onto page. Finds gyms that match user's zip code and add to list.
        if(user.getUserDetail() != null) {
            for (Gym gym : gyms) {
                if (gym.getAddress().getZipCode() == user.getUserDetail().getAddress().getZipCode()) {
                    matchingGyms.add(gym);
                }
            }
        }

        //iterates through friends to find their respective user instance. Get workouts from particular
        // user and then adds workouts to a list.
        for (String friend : user.getFriends()) {
            User myFriend = userRepository.findByUsername(friend);

            ArrayList<Workout> tempWorkout = new ArrayList<>();
            for (Workout workout : myFriend.getWorkouts()) {
                if (myFriend.getWorkouts().isEmpty()) {
                    break;
                } else {
                    tempWorkout.add(workout);
                }
            }

            if (tempWorkout.isEmpty()) {
                break;
            } else {

                //workout list is then sorted from newest to oldest.
                Collections.sort(tempWorkout, (c1, c2) -> {
                    if (c1.getTimeStamp().after(c2.getTimeStamp())) return -1;
                    else return 1;
                });

                //takes newest workout from each user(friend/buddy) and adds it to list

                sharedWorkouts.add(tempWorkout.get(0));
            }


        }
                for (Workout workout : user.getWorkouts()) {
                    if(user.getWorkouts().isEmpty()) {
                        break;
                    } else {
                    userWorkouts.add(workout);
                    }
                }

                if(!userWorkouts.isEmpty()){

                    Collections.sort(userWorkouts, (c1, c2) -> {
                        if (c1.getTimeStamp().after(c2.getTimeStamp())) return -1;
                        else return 1;
                        });
                }


        /*
        User is directed to the user index page after a successful login is completed.
        The variable loggedIn is used to display certain links if user is logged in.
        All list are then assigned attributes to be passed into view.
         */

        model.addAttribute("title", "Welcome!!");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("userPhoto", userPhoto.getProfilePic());
        model.addAttribute("receivedMessages", receivedMessages);
        model.addAttribute("sentMessages", sentMessages);
        model.addAttribute("matchingParks", matchingParks);
        model.addAttribute("matchingGyms", matchingGyms);
        model.addAttribute("workouts", userWorkouts);
        model.addAttribute("backgroundImage", image.randomImageGenerator());


        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        model.addAttribute("shared", sharedWorkouts);

        return "user/index";

    }

    @GetMapping("addprofile/{userId}")
    public String displayEditProfileForm(Model model, @PathVariable int userId) throws JsonProcessingException {

        /*
        User is directed to add profile from link if the boolean isNew is true. If false they'll be
        directed to the profile page.

         */

        Optional<User> result = userRepository.findById(userId);
        User updateUser = result.get();

        model.addAttribute("title", "Update " + updateUser.getUsername() + "'s profile");
        model.addAttribute("user", updateUser);
        model.addAttribute("loggedIn", true);
        model.addAttribute("sexes", UserSex.values());
        if (updateUser.getUserDetail() != null) {
            if (updateUser.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(updateUser.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        return "user/addprofile";
    }

    @PostMapping("addprofile")
    public String processEditProfileForm(@ModelAttribute @Valid User user, Errors errors, Model model) throws JsonProcessingException {

        Optional<User> result = userRepository.findById(user.getId());
        User updatedUser = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update " + updatedUser.getUsername() + "'s profile");
            model.addAttribute("user", updatedUser);
            model.addAttribute("sexes", UserSex.values());
            model.addAttribute("loggedIn", true);
            model.addAttribute("userId", user.getId());

            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }

            return "user/addprofile";
        }

//        userRepository.deleteById(userId);

        // Once user adds detailed information to profile it sets isNew to false. From there when they select
        // profile it will take them to the profile page.

        user.setNew(false);

        //If the user's birthday is set it will set age using function below. If not, no age is set. Check user detail
        //class for function.
        if (user.getUserDetail().getBirthDay() != null) {
            user.getUserDetail().getAgeFromBirthDate(user);
        }

        // User info is updated and saved to the database with new information
       userRepository.save(user);

        return "redirect:/user/index";
    }

    @GetMapping("profile")
    public String userProfile(HttpServletRequest request, Model model) throws JsonProcessingException {

        // This page is only displayed if the user variable isNew is false.

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", user.getUserDetail().getFirstName() + "'s Profile");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("userPhoto", user.getUserPhoto());


        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }


        return "user/profile";
    }

    @GetMapping("bmi")
    public String bmiCalc(HttpServletRequest request, Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getHeight() < 1 || user.getUserDetail().getWeight() < 1) {
                model.addAttribute("title", "BMI Page");
                model.addAttribute("user", user);
                    if (user.getUserDetail().getAddress().getZipCode() > 1) {
                        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                    } else {
                        model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                    }


                return "/user/bmiNoDetail";
            }

        } else {
            model.addAttribute("title", "BMI Page");
            model.addAttribute("user", user);
            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }

            return "/user/bmiNoDetail";
        }


        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }

        model.addAttribute("title", "BMI Calculator");
        model.addAttribute("bmi1", user.getUserDetail().getBodyMassIndex());
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);

        return "user/bmi";

    }

    @GetMapping("bmiNoDetail")
    public String bmiCalc(Model model, HttpServletRequest request) throws JsonProcessingException {

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

        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("title", "BMI Calculator");
        return "user/bmiNoDetail";
    }

    @GetMapping("addfriend")
    public String displayAddFriendForm(HttpServletRequest request, Model model) throws JsonProcessingException {

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

        model.addAttribute("title", "Add Workout Buddy");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("friend", new Friend());

        return "user/addfriend";

    }

    @PostMapping("addfriend")
    public String renderAddFriendForm(@ModelAttribute @Valid Friend friend, HttpServletRequest request,
                                      Model model, Errors errors) throws JsonProcessingException {
        User user = (User) getUserFromSession(request.getSession());

        User validateFriend = userRepository.findByUsername(friend.getUserName());

        if(validateFriend == null) {

            errors.rejectValue("userName", "userName.invalid", "That user doesn't" +
                    "exist!!");
            model.addAttribute("title", "Add Workout Buddy");
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("friend", friend);

            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }

            return "user/addfriend";
        }

        if(user.getFriends().contains(validateFriend.getUsername())) {
            errors.rejectValue("userName", "userName.invalid", "That user is already your" +
                    "workout buddy!");
            model.addAttribute("title", "Add Workout Buddy");
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("friend", friend);

            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }

            return "user/addfriend";
        }


        user.getFriends().add(validateFriend.getUsername());
        userRepository.save(user);

        return "redirect:/user/index";
    }

}
