package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.service.RatingEventService;
import com.example.demo1_pbl4.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingEventService ratingEventService;

    @Autowired
    private UserEventService userEventService;


    // day la controller cho viec rating event, hoac host/user.
    public String processRating(@ModelAttribute("Rating") Rating ratingEvent) {
        ratingEventService.insertRating(ratingEvent);
        return "process_register";
    }

    //
    @GetMapping("/mem_of_event/{eventId}")
    public String showAllMemOfEvent(Model model, @PathVariable("eventId") Long eventId) {
        System.out.println(eventId);
        List<MemberInRating> members = ratingEventService.findMemberInEvent(eventId, "Member");
        // System.out.println("member="+members);
        model.addAttribute("members", members);
       // model.addAttribute("name", member.getFirstName() + " " + member.getLastName());
//        Long userId=1;
//        Rating rating = ratingEventService.findRatingByUserAndEvent(userId, eventId);
//        System.out.println("rating=" + rating);
//        //   model.addAttribute("myRating", new Rating());
//        if (rating != null) {
//            model.addAttribute("myRating", rating);
//        } else {
//            model.addAttribute("myRating", new Rating());
//        }
        return "rating/rate_all_member";
    }

    //BachLT: Hiển thị form đánh giá member cho host.
    @GetMapping("/member/{userId}&{eventId}")
    public String rateMemberByHost(Model model, @PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId) {
        MemberInRating member = ratingEventService.findMemberInEventById(userId, eventId, "Member");
        model.addAttribute("member", member);
        model.addAttribute("name", member.getFirstName() + " " + member.getLastName());
        model.addAttribute("eventId", eventId);
        model.addAttribute("userId", userId);
//        Rating rating = ratingEventService.findRatingByUserAndEvent(userId, eventId).get();
//        System.out.println("rating=" + rating);
     //   model.addAttribute("myRating", new Rating());
//        if (rating != null) {
//            model.addAttribute("myRating", rating);
//        } else {
//            model.addAttribute("myRating", new Rating());
//        }
        return "rating/rating_form";
    }

    @PostMapping("/rating_member")
    public String ratingMember(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId, @ModelAttribute("myRating") Rating myRating
            , @RequestParam("point4") int point4, @RequestParam("point5") int point5
            , @RequestParam("point6") int point6) {
        myRating.setMemberPoint(point4, point5, point6);
        UserEvent userEvent= userEventService.getUserEventById(userId,eventId);
        System.out.println("userId= "+userId);
        System.out.println("eventId"+eventId);
        myRating.setUserEvent(userEvent);
        Rating rating=ratingEventService.findRatingByUserAndEvent(userId, eventId);// lay rating hien tai
//        System.out.println(rating.getIdRate());
        if (rating  != null) {
            rating.setPoint4(myRating.getPoint4());
            rating.setPoint5(myRating.getPoint5());
            rating.setPoint6(myRating.getPoint6());
            ratingEventService.updateRating(rating);
        } else {
            ratingEventService.insertRating(myRating);
        }
        return "redirect:/rating/mem_of_event/" + eventId;
    }
}
