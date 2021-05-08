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
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
            Date currentDate = Calendar.getInstance().getTime();

//            Message message = userMessage.getMessage();
//            User sender = userMessage.getUser();

            if(errors.hasErrors()) {
                model.addAttribute("title", "Send Message");
                return "message/addmessage";
            }

            User recipient = userRepository.findByUsername(newMessage.getRecipient());

            if (recipient == null) {
                errors.rejectValue("recipient", "recipient.invalid", "The provided " +
                        "recipient doesn't exist");
                model.addAttribute("title", "Send Message");
                return "message/addmessage";
            }

            Message message = new Message(newMessage.getBody(), newMessage.getRecipient(),
                    user.getUsername(), currentDate);

            messageRepository.save(message);

            model.addAttribute("user", user);
            return "redirect:/user/index";
        }

        @GetMapping("message/index")
        public String messageIndex(Model model, HttpServletRequest request){

            User user = (User) getUserFromSession(request.getSession());


            

            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
//            model.addAttribute("messages", MessageRepository.findAll());
            model.addAttribute("title", "Message List");
            model.addAttribute("messages", messageRepository.findAll());


            return "message/index";

        }


    }