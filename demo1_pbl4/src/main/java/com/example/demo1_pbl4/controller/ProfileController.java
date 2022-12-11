package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating_Event;
import com.example.demo1_pbl4.service.RatingEventService;
import com.example.demo1_pbl4.service.RatingMemberService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private RatingEventService ratingEventService;


    private UserService userService;

    @GetMapping("")
    public String showListRating(Model model, HttpSession session) {
        String username = session.getAttribute("username").toString();
        Long userId = userService.findUserByUsername(username).getUserId();
        List<Rating_Event> listRatingEvent = ratingEventService.findRatingByUserId(userId);
//        for(Rating_Event re: listRatingEvent)
//        {
//            re.setEventname(re.getEvent().getEventName());
//            re.setBeginTime(re.getEvent().getBeginTime());
//            re.setEndTime(re.getEvent().getEndTime());
//        }
        model.addAttribute("RatingHistory", listRatingEvent);
        model.addAttribute("myUser", userService.getUserById(userId));
        return "Profile";
    }
}
