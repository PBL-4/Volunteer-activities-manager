package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Rating_Event;
import com.example.demo1_pbl4.service.RatingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingEventService ratingEventService;
    private RatingMemberService ratingMemberService;

    // day la controller cho viec rating event, hoac host/user.
    public String processRating(@ModelAttribute("Rating") Rating_Event ratingEvent)
    {
        ratingEventService.insertRating(ratingEvent);
        return "process_register";
    }

}
