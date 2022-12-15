package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.*;
import com.example.demo1_pbl4.service.EventService;
import com.example.demo1_pbl4.service.PostService;
import com.example.demo1_pbl4.service.UserEventService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
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

    @Autowired
    private UserEventService userEventService;
    @Autowired
    private ServletContext context;

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
        int currentPage = 0, pageSize = 2;
        Page<Event> eventPages = eventService.findEventWithPagination(currentPage, pageSize);
        long totalItems = eventPages.getTotalElements();
        int totalPages = eventPages.getTotalPages();
        List<Event> eventLists = eventPages.getContent();
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("eventList", eventLists);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("myTotalPages", totalPages - 1);
        return "/event/find_event_list";
    }

    @GetMapping("/page/{pageNumber}")
    public String pagingEventPage(Model model, @PathVariable("pageNumber") int currentPage) {
        int pageSize = 2;
        Page<Event> eventPages = eventService.findEventWithPagination(currentPage, pageSize);
        long totalItems = eventPages.getTotalElements();
        int totalPages = eventPages.getTotalPages();
        List<Event> eventLists = eventPages.getContent();
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("myTotalPages", totalPages - 1);
        model.addAttribute("eventList", eventLists);
        model.addAttribute("currentPage", currentPage);
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
            } else if (choice.equals("location")) {
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
            , @RequestParam("content") String content
    ) {
        if ((session.getAttribute("username")) != null) {
        User user = userService.findUserByUsername(session.getAttribute("username").toString());

            Long millis = System.currentTimeMillis();
            Date datePost = new Date(millis);
            Post post = new Post(eventName, content, datePost, user);
            String hostname=user.getLastName();
            Event event = new Event(eventName, location, agePermit, numOfMem, beginDate, endDate, hostname, donation, post);
            event.setStatus(new Status(1L, "Chưa bắt đầu"));
            post.setEvent(event);
            eventService.insertEvent(event);
            postService.insertPost(post);
            UserEvent ue =new UserEvent(event,user,"Host",true);
            UserEvent checkOne=userEventService.insertUser(ue);
            if(checkOne!=null)
            {
                System.out.println("Thêm mới thành công");
            }
        //    eventService.createEventByHost(user.getUserId(), event.getEventId(), "Host", true);
            System.out.println("eventId=" + event.getEventId());
          //  return "redirect:/"+ context.getContextPath()+"/my_event";
            return "redirect:/events/my_event";
        } else {
            System.out.println("Chua dang nhap");
            model.addAttribute("message","Bạn chưa xác thực để thực hiện hành động này");
            return "/403Page";
        }
    }

    @GetMapping("/admin")
    public String showEventOnAdmin(Model model) {
        List<Event> eventLists = eventService.getAllEvents();
        model.addAttribute("Events", eventLists);
        return "/admin/EventsManager";
    }

    @PostMapping("/admin")
    public String View(Model model, @RequestParam("eventName") String keyword) {
        List<Event> listevent = eventService.findEventByEventName(keyword);
        model.addAttribute("Events", listevent);
        model.addAttribute("eventName", keyword);

        return "admin/EventsManager";
    }

    //BachLT: Quản lý danh sách hoạt động đang tham gia: trang đầu tiên của recuite volunteer
    @GetMapping("/my_event")
    public String showMyHostEvent(Model model, HttpSession session) {
        User user = null;
        int currentPage=0;
        Pageable pageable = PageRequest.of(0,10);
        if (session.getAttribute("username") != null) {
            String username = session.getAttribute("username").toString();
            user = userService.findUserByUsername(username);
        }
        if (user != null) {
            Page<Event> eventPages = eventService.findHostOfEvent(user.getUserId(), "host", pageable);
            List<Event> eventList = eventPages.getContent();
            if (eventList.isEmpty()) {
                model.addAttribute("message", "Không có dữ liệu ");
            } else {
                model.addAttribute("events", eventList);
            }
            return "event/my_event";
        } else {
            System.out.println(context.getContextPath());
            System.out.println("Chưa đăng nhập");
            return "redirect:/login";
        }
    }
}
