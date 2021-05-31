package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.MessageRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.Message;
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
import java.util.*;

@Controller
//@RequestMapping("message")
public class MessageController {

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
        MessageRepository messageRepository;


        @GetMapping("message/addmessage")
        public String displayAddMessage(Model model, HttpServletRequest request){

            User user = (User) getUserFromSession(request.getSession());
            
//            User recipient = null;

            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("title", "Send Message");
            model.addAttribute("message", new Message());
//            model.addAttribute("recipients", userRepository.findAll());
//            MessageDTO userMessage = new MessageDTO();
//            userMessage.setUser(user);
//            model.addAttribute("userMessage", userMessage);

            return "message/addmessage";

        }

        @PostMapping("message/addmessage")
        public String processAddMessage(@ModelAttribute @Valid Message newMessage, Errors errors, Model model,
                                        HttpServletRequest request) {

            User user = (User) getUserFromSession(request.getSession());

            //Pulling current date info from Date class to add to message instance.
            Date currentDate = Calendar.getInstance().getTime();
            Date dateInSecs = user.truncToSec(currentDate);
//            Message message = userMessage.getMessage();
//            User sender = userMessage.getUser();

            //Query the user repository to see if the entered user exists.
            User recipient = userRepository.findByUsername(newMessage.getRecipient());

            if (recipient == null) {
                errors.rejectValue("recipient", "recipient.invalid", "The provided " +
                        "recipient doesn't exist");
                model.addAttribute("title", "Send Message");
                model.addAttribute("user", user);
                model.addAttribute("loggedIn", true);
                model.addAttribute("message", new Message());
                return "message/addmessage";
            }

            if(errors.hasErrors()) {
                model.addAttribute("title", "Send Message");
                model.addAttribute("user", user);
                model.addAttribute("loggedIn", true);
                model.addAttribute("message", new Message());
                return "message/addmessage";
            }

            /*
            We set the user as the sender below, we set the time current date as the time stamp and the
            other attributes are from the form from the view.
             */

            Message message = new Message(newMessage.getBody(), recipient.getUsername(),
                    user.getUsername(), dateInSecs);

            messageRepository.save(message);

            model.addAttribute("user", user);
            return "redirect:/user/index";
        }

        @GetMapping("message/index")
        public String messageIndex(Model model, HttpServletRequest request){

            User user = (User) getUserFromSession(request.getSession());
            ArrayList<Message> messages = (ArrayList<Message>) messageRepository.findAll();

            /* Populating Arraylist with only logged in user's messages. This will be for a view of all
               messages. Ordered by date of course.
            */
            ArrayList<Message> userMessages = new ArrayList<>();

            for (Message message : messages) {
                if(message.getRecipient().equals(user.getUsername()) || message.getSender().equals(user.getUsername())) {
                    userMessages.add(message);
                }
            }

            // Sorting methods to sort messages newest, to oldest.
            Collections.sort(userMessages, (c1, c2) -> {
                if (c1.getDate().after(c2.getDate())) return -1;
                else return 1;
            });
            

            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
//            model.addAttribute("messages", MessageRepository.findAll());
            model.addAttribute("title", "Messages");
            model.addAttribute("messages", userMessages);


            return "message/index";

        }


    }
