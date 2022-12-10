package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.Status;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.EventService;
import com.example.demo1_pbl4.service.PostService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/list")
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


    @GetMapping("/find_all")
    public String findEventPage(Model model) {
        List<Event> eventLists = eventService.getAllEvents();
        model.addAttribute("eventList", eventLists);
        return "/event/find_event_list";
    }

    @GetMapping("/page")
    public String pagingEvent(Model model) {
        int currentPage=0,pageSize=2;
        Page<Event> eventPages = eventService.findEventWithPagination(currentPage,pageSize);
        long totalItems= eventPages.getTotalElements();
        int totalPages= eventPages.getTotalPages();
        List<Event> eventLists=eventPages.getContent();
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("eventList", eventLists);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("myTotalPages", totalPages-1);
        return "/event/find_event_list";
    }
    @GetMapping("/page/{pageNumber}")
    public String pagingEventPage(Model model, @PathVariable("pageNumber") int currentPage) {
        int pageSize=2;
        Page<Event> eventPages = eventService.findEventWithPagination(currentPage,pageSize);
        long totalItems= eventPages.getTotalElements();
        int totalPages= eventPages.getTotalPages();
        List<Event> eventLists=eventPages.getContent();
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("myTotalPages", totalPages-1);
        model.addAttribute("eventList", eventLists);
        model.addAttribute("currentPage",currentPage);
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
    public String showEventsByFind(Model model, @RequestParam(value = "choice", required = false) String choice, @RequestParam("keyword") String keyword) {
        List<Event> eventLists;
        if (choice != null) {
            if (choice.equals("eventName")) {
                eventLists = eventService.findEventByEventName(keyword);
            } else if (choice.equals("eventName")) {
                eventLists = eventService.findEventByLocation(keyword);
                model.addAttribute("location", keyword);
            } else if (choice.equals("hostname")) {
                eventLists = eventService.findEventByHostname(keyword);
            } else {
                eventLists = eventService.getAllEvents();
            }
        } else {
            eventLists = eventService.getAllEvents();
        }
        model.addAttribute("eventList", eventLists);

        //     model.addAttribute("keyword", keyword);
        return "/event/find_event_list";
    }

    @GetMapping("/create_event")
    public String showCreateEventForm(Model model, HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "/event/create_event_host";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/create_event")
    public String createEvent(Model model, HttpSession session, @RequestParam("eventName") String eventName
            , @RequestParam("location") String location
            , @RequestParam("beginDate") Date beginDate
            , @RequestParam("endDate") Date endDate
            , @RequestParam("numOfMem") int numOfMem
            , @RequestParam("agePermit") int agePermit
            , @RequestParam("donation") double donation
            , @RequestParam("hostname") String hostname
            , @RequestParam("content") String content
    ) {

        User user = userService.findUserByUsername(session.getAttribute("username").toString());
        if (user != null) {
            Long millis = System.currentTimeMillis();
            Date datePost = new Date(millis);
            Post post = new Post(eventName, content, datePost, user);
            Event event = new Event(eventName, location, agePermit, numOfMem, beginDate, endDate, hostname, donation, post);
            event.setStatus(new Status(1, "Chưa bắt đầu"));
            post.setEvent(event);
            eventService.insertEvent(event);
            postService.insertPost(post);
            return "redirect:/list ";
        } else {
            System.out.println("Chua dang nhap");
            return "/403Page";
        }

    }

}
