package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.service.RatingEventService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("")
    public String showListRating(Model model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            String username = session.getAttribute("username").toString();
            Long userId = userService.findUserByUsername(username).getUserId();
            List<Rating> listRatingEvent = ratingEventService.findRatingByUserId(userId);
//        for(Rating_Event re: listRatingEvent)
//        {
//            re.setEventname(re.getEvent().getEventName());
//            re.setBeginTime(re.getEvent().getBeginTime());
//            re.setEndTime(re.getEvent().getEndTime());
//        }
            model.addAttribute("RatingHistory", listRatingEvent);
            model.addAttribute("myUser", userService.getUserById(userId));
            return "profile/Profile";
        } else {
            System.out.println("Chua dang nhap");
            return "redirect:/login";
        }
    }
}
