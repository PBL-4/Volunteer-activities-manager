package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    // day la controller cho viec rating event, hoac host/user.


}
