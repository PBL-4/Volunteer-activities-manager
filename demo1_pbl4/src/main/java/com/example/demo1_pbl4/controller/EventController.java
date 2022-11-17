package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EventController {
    @Autowired
    private EventService eventService;

}
