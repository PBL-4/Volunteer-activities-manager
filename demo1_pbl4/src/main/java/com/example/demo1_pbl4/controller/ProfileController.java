package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.service.RatingService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private RatingService ratingService;
    private UserService userService;

    @GetMapping("")
    public String showListRating(Model model,@RequestParam("userId") Long userId) {
        model.addAttribute("RatingHistory", ratingService.getRatingById(userId));
        model.addAttribute("myUser", userService.getUserById(userId));
        return "Profile";
    }
}
