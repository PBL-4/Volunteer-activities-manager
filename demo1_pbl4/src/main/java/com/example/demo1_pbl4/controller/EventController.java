package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("")
    public String showAllEvents(Model model) {
        List<Event> eventLists = eventService.getAllEvents();
        model.addAttribute("eventList", eventLists);
        return "/event/event_list";
    }

    @GetMapping("/id")
    public String showEventsById(Model model, int id) {

        return "/event/event_list";
    }

    @GetMapping("/id1")
    public String showEventsById1(Model model, int id) {
        List<Event> eventLists = eventService.getAllEvents();
        model.addAttribute("eventList", eventLists);
        return "/event/event_list";
    }

    @PostMapping("/insert")
    public String showInsertForm() {
        return "/event/insert_new_event";
    }

    @GetMapping("/find")
    public String findEventPage(Model model) {
        List<Event> eventLists = eventService.getAllEvents();
        model.addAttribute("eventList", eventLists);
        return "/event/find_event_list";
    }

    @GetMapping("/find?l={location}&k={keyword}")
    public String showAllEventsByFind(Model model, @RequestParam("location") String location,
                                      @RequestParam("keyword") String keyword) {
        List<Event> eventLists;
        if (location != null || keyword != null) {
            eventLists = eventService.findEventByLocationAndKeyword(location, keyword);
        } else {
            eventLists = eventService.getAllEvents();
        }

        model.addAttribute("eventList", eventLists);
        model.addAttribute("location", location);
        //     model.addAttribute("keyword", keyword);
        return "/event/find_event_list";
    }
    @PostMapping("/find")
    public String showEventsByFind(Model model, @RequestParam(value="choice", required=false) String choice, @RequestParam("keyword") String keyword) {
        List<Event> eventLists;
        if (choice.equals("eventName")) {
            eventLists = eventService.findEventByEventName(keyword);
        } else if(choice.equals("eventName")) {
            eventLists = eventService.findEventByLocation(keyword);
            model.addAttribute("location", keyword);
        }
        else if(choice.equals("hostname")) {
            eventLists = eventService.findEventByHostname(keyword);
        }
        else{
            eventLists = eventService.getAllEvents();
        }
        model.addAttribute("eventList", eventLists);

        //     model.addAttribute("keyword", keyword);
        return "/event/find_event_list";
    }
}
