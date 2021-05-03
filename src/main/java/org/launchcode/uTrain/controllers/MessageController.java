package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.MessageRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.Message;
import org.launchcode.uTrain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("message")
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


        @GetMapping("addmessage")
        public String displayAddMessage(Model model, HttpServletRequest request){

            User user = (User) getUserFromSession(request.getSession());

            String recipient = null;

            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("title", "Add Message");
            model.addAttribute(new Message());
            model.addAttribute("recipient", recipient);
            model.addAttribute("sender", user.getUsername());

            return "message/addmessage";
        }

        @PostMapping("addmessage")
        public String processAddMessage(@ModelAttribute @Valid Message newMessage, Errors errors, Model model, HttpServletRequest request) {

//            ,String recipient
            User user = (User) getUserFromSession(request.getSession());
//            User userRecipient = userRepository.findByEmail(recipient);

            if(errors.hasErrors()) {
                model.addAttribute("title", "Add Message");
                return "message/addmessage";
            }

//            Message msgRefactor = new Message(newMessage.getBody(), userRecipient.getUsername(), user.getUsername(), newMessage.getDate());
//
//            userRecipient.getMessages().add(msgRefactor);



            messageRepository.save(newMessage);
            return "redirect:index";
        }

        @GetMapping("index")
        public String messageIndex(Model model, HttpServletRequest request){

            User user = (User) getUserFromSession(request.getSession());

            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("messages", messageRepository.findAll());
            model.addAttribute("title", "Message List");
            model.addAttribute("messages", user.getMessages());

            return "message/index";
        }


    }
