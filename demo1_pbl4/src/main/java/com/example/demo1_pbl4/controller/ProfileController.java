package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.RatingEventService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/my_account")
public class ProfileController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/images/profile";
    @Autowired
    private RatingEventService ratingEventService;

    @Autowired
    private UserService userService;

    //BachLT- Có fix lại
    @GetMapping("{uId}")
    public String showListRating(Model model, HttpSession session, @PathVariable("uId") Long uId) {
        if (session.getAttribute("user") != null) {
            User myUser = (User) session.getAttribute("user");
            User user = userService.getUserById(uId);
            List<Rating> listRatingEvent = ratingEventService.findRatingByUserId(user.getUserId());
            model.addAttribute("RatingHistory", listRatingEvent);
            model.addAttribute("myUser", userService.getUserById(user.getUserId()));
            model.addAttribute("userEdit", userService.getUserById(user.getUserId()));
            model.addAttribute("validUser",myUser.getUserId().equals(user.getUserId()));
            return "profile/Profile";
        } else {
            System.out.println("Chua dang nhap");
            return "redirect:/login";
        }
    }

    @PostMapping("/edit_user")
    public String editUser(Model model, @ModelAttribute(value = "userEdit") User user) {
        // If you use it, try again with other solution.
//        User user1 = userService.updateUser(user);
//        System.out.println(user1.getUserId());
        User myNewUser = userService.getUserById(user.getUserId());
        myNewUser.setFirstName(user.getFirstName());
        myNewUser.setLastName(user.getLastName());
        myNewUser.setEmail(user.getEmail());
        myNewUser.setPhoneNum(user.getPhoneNum());
        myNewUser.setGender(user.getGender()); // error soon
        myNewUser.setAddress(user.getAddress());
        userService.updateUser(myNewUser);
        return "redirect:/my_account";
    }

    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file, HttpSession session
            , RedirectAttributes redirAttrs) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        fileNames.append(file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());

        User user = (User) session.getAttribute("user");
        user.setAvatar("/images/profile/" + fileNames.toString());
        userService.updateUser(user);

        redirAttrs.addFlashAttribute("msg", "Uploaded images success");
        redirAttrs.addFlashAttribute("myUser", user);
        model.addAttribute("msg", "Uploaded images success");
        System.out.println("Uploaded images: " + fileNames.toString());
        return "redirect:/my_account/" + user.getUserId();
    }
}
