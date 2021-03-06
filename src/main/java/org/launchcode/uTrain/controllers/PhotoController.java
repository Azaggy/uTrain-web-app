package org.launchcode.uTrain.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.launchcode.uTrain.data.PhotoRepository;
import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.LiveWeatherService;
import org.launchcode.uTrain.models.user.UserPhoto;
import org.launchcode.uTrain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class PhotoController {

    private static final String userSessionKey = "user";

    public PhotoController(LiveWeatherService liveWeatherService) {
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
//    public UserPhoto getUserPhotoFromSession(HttpSession session) {
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        if (userId == null) {
//            return null;
//        }
//        Optional<UserPhoto> userPhoto = photoRepository.findById(userId);
//        if (userPhoto.isEmpty()) {
//            return null;
//        }
//
//        return userPhoto.get();
//    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhotoRepository photoRepository;


    private final LiveWeatherService liveWeatherService;

//    String fileName;
//    @PostMapping("user/profilePhoto")
//    public RedirectView saveUserPhoto(UserPhoto userPhoto, HttpServletRequest request,
//                                     @RequestParam("image")MultipartFile multipartFile) throws IOException {
//
//        User user = (User) getUserFromSession(request.getSession());
//        userPhoto.setUser(user);
//
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
////        userPhoto.setProfilePic(fileName);
//
//        UserPhoto savedPhoto = photoRepository.save(userPhoto);
////        User savedUser = userRepository.save(user);
//
//        String uploadDir = "profilePic/" + savedPhoto.getProfilePic();
//
//        Path uploadPath = Paths.get(uploadDir);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileName);
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException ioException) {
//            throw new IOException("could not save image file: " + fileName, ioException);
//        }
////        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//        return new RedirectView("/user/profilePhoto", true);
//    }

    @GetMapping("profilePhoto")
    public String avatarPage(HttpServletRequest request, Model model) throws JsonProcessingException {

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "Photo Selection");
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


        return "user/profilePhoto";
    }


    @GetMapping("profilePhoto/")
    public String displayEditProfilePhotoForm(Model model, HttpServletRequest request, UserPhoto userPhoto) throws JsonProcessingException {


        User user = (User) getUserFromSession(request.getSession());
        userPhoto.setUser(user);
        Optional<User> result = userRepository.findById(user.getId());
        User updateUser = result.get();

        model.addAttribute("title", "Update " + updateUser.getUsername());
        model.addAttribute("user", updateUser);
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);
        model.addAttribute("profilePic", user.getUserPhoto());
        if (user.getUserDetail() != null) {
            if (user.getUserDetail().getAddress().getZipCode() > 1) {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }
        } else {
            model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
        }


        return "user/profilePhoto";
    }

    @PostMapping("profilePhoto")
    public String SavePhotoForm(@ModelAttribute @Valid UserPhoto userPhoto, RedirectAttributes ra,
                                         @RequestParam("image") MultipartFile multipartFile,
                                         Errors errors, Model model, HttpServletRequest request) throws IOException {
        User user = (User) getUserFromSession(request.getSession());
        userPhoto.setUser(user);
        user.setUserPhoto(userPhoto);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        userPhoto.setProfilePic(fileName);
        UserPhoto savedPhoto = photoRepository.save(userPhoto);
        User savedUser = userRepository.save(user);

        String uploadDir = "./src/main/resources/templates/user/profilePic/";

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e){
            throw new IOException("Could not save" + fileName);
        }


        Optional<User> result = userRepository.findById(user.getId());
        User updatedUser = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update " + updatedUser.getUsername());
            model.addAttribute("user", user);
            model.addAttribute("loggedIn", true);
            model.addAttribute("userId", user.getId());
            errors.rejectValue("image","image.fail","Issue with uploading the image.");
            if (user.getUserDetail() != null) {
                if (user.getUserDetail().getAddress().getZipCode() > 1) {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(user.getUserDetail().getAddress().getZipCode(), "us"));
                } else {
                    model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
                }
            } else {
                model.addAttribute("currentWeather", liveWeatherService.getCurrentWeather(63101, "us"));
            }


            return "user/profilePhoto";
        }
        model.addAttribute("userPhoto", userPhoto);

        photoRepository.save(userPhoto);

        return "redirect:/user/profile";
    }
}
