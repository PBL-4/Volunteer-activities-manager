package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.service.RatingEventService;
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


    // day la controller cho viec rating event, hoac host/user.
    public String processRating(@ModelAttribute("Rating") Rating ratingEvent) {
        ratingEventService.insertRating(ratingEvent);
        return "process_register";
    }

    //
    @GetMapping("/mem_of_event/{eventId}")
    public String showAllMemOfEvent(Model model,@PathVariable ("eventId")Long eventId)
    {
        System.out.println(eventId);
        List<MemberInRating> members=ratingEventService.findMemberInEvent(eventId,"Member");
        // System.out.println("member="+members);
        model.addAttribute("members",members);
        return "rating/rate_all_member";
    }
    //BachLT: Hiển thị form đánh giá member cho host.
    @GetMapping("/member/{id}")
    public String rateMemberByHost(Model model, @PathVariable("id") Long id, @RequestParam("eventId")Long eventId) {
        List<MemberInRating> members=ratingEventService.findMemberInEvent(eventId,"Member");
        model.addAttribute("member",members);
        return "rating_form";
    }
}
