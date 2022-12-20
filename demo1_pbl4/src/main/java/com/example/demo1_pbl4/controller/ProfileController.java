package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.RatingEventService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/my_account")
public class ProfileController {
    @Autowired
    private RatingEventService ratingEventService;

    @Autowired
    private UserService userService;

    //BachLT- Có fix lại
    @GetMapping("")
    public String showListRating(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            List<Rating> listRatingEvent = ratingEventService.findRatingByUserId(user.getUserId());
            model.addAttribute("RatingHistory", listRatingEvent);
            model.addAttribute("myUser", userService.getUserById(user.getUserId()));
            model.addAttribute("userEdit", userService.getUserById(user.getUserId()));
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
}
