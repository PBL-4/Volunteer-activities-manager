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
    public String showDefaultPage(Model model,
                                  @RequestParam(value = "sort", required = false) Integer sort,
                                  @RequestParam(value = "choice", required = false) String choice,
                                  @RequestParam(value = "keyword", required = false) String keyword) {
        try {
            int pageNumber = 0;
            if (choice == null) choice = "eventName";
            if (sort == null) sort = 1;
            if (keyword == null) keyword = "";
            int c = 1;

            Pageable pageable = PageRequest.of(pageNumber, 10);
            Page<Event> pageEvents = eventService.findEventOrderByEventName(pageable);
            if (!keyword.equals("")) {
                if (choice.equals("eventName")) {
                    c = 1;
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventByEventNameOrderByEventName(keyword, pageable);
                            break;
                        }
                        case 2: {

                            pageEvents = eventService.findEventByEventNameOrderByBeginTime(keyword, pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventByEventNameOrderByPopular(keyword, pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventByEventNameOrderByEventName(keyword, pageable);
                    }
                } else if (choice.equals("location")) {
                    c = 2;
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventByLocationOrderByEventName(keyword, pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventByLocationOrderByBeginTime(keyword, pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventByLocationOrderByPopular(keyword, pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventByLocationOrderByEventName(keyword, pageable);
                    }
                } else if (choice.equals("hostname")) {
                    c = 3;
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventByHostnameOrderByEventName(keyword, pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventByHostnameOrderByBeginTime(keyword, pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventByHostnameOrderByPopular(keyword, pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventByHostnameOrderByEventName(keyword, pageable);
                    }
                } else {
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventOrderByEventName(pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventOrderByBeginTime(pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventOrderByPopular(pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventOrderByEventName(pageable);
                    }
                }
            } else {
                switch (sort) {
                    case 1: {
                        pageEvents = eventService.findEventOrderByEventName(pageable);
                        break;
                    }
                    case 2: {
                        pageEvents = eventService.findEventOrderByBeginTime(pageable);
                        break;
                    }
                    case 3: {
                        pageEvents = eventService.findEventOrderByPopular(pageable);
                        break;
                    }
                    default:
                        pageEvents = eventService.findEventOrderByEventName(pageable);
                }
            }
            List<Event> eventLists;
            long totalItems = pageEvents.getTotalElements();
            int totalPages = pageEvents.getTotalPages();
            eventLists = pageEvents.getContent();
            if (eventLists.isEmpty()) {
                model.addAttribute("message", "Kh??ng c?? d??? li???u c?? s???n");
            } else {
                model.addAttribute("totalItems", totalItems);
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("eventList", eventLists);
                model.addAttribute("pageNumber", pageNumber);
                model.addAttribute("myTotalPages", totalPages - 1);
            }
            model.addAttribute("eventList", eventLists);
            model.addAttribute("sort", sort);
            model.addAttribute("keyword", keyword);
            model.addAttribute("choice", choice);
            model.addAttribute("c", c);
            System.out.println("sort1=" + sort);
            System.out.println("keyword=" + keyword);
            System.out.println("choice" + choice);
            return "/event/find_event_list";
        } catch (Exception e) {
            e.printStackTrace();
            return "500Page";
        }
    }

    @GetMapping("/page/{pageNumber}")
    public String showEventsByFind(Model model, @PathVariable(value = "pageNumber", required = false) Integer pageNumber, @RequestParam(value = "sort") Integer sort, @RequestParam(value = "choice", required = false)
            String choice, @RequestParam(value = "keyword", required = false) String keyword) {
        try {

            if (pageNumber == null) pageNumber = 0;
            if (sort == null) sort = 1;
            if (keyword == null) keyword = "";
            //if(choice==null) choice="eventName";
            Pageable pageable = PageRequest.of(pageNumber, 10);
            Page<Event> pageEvents = null;
            List<Event> eventLists;
            if (!keyword.equals("")) {
                if (choice.equals("eventName")) {
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventByEventNameOrderByEventName(keyword, pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventByEventNameOrderByBeginTime(keyword, pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventByEventNameOrderByPopular(keyword, pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventByEventNameOrderByEventName(keyword, pageable);
                    }
                } else if (choice.equals("location")) {
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventByLocationOrderByEventName(keyword, pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventByLocationOrderByBeginTime(keyword, pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventByLocationOrderByPopular(keyword, pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventByLocationOrderByEventName(keyword, pageable);
                    }
                } else if (choice.equals("hostname")) {
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventByHostnameOrderByEventName(keyword, pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventByHostnameOrderByBeginTime(keyword, pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventByHostnameOrderByPopular(keyword, pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventByHostnameOrderByEventName(keyword, pageable);
                    }
                } else {
                    switch (sort) {
                        case 1: {
                            pageEvents = eventService.findEventOrderByEventName(pageable);
                            break;
                        }
                        case 2: {
                            pageEvents = eventService.findEventOrderByBeginTime(pageable);
                            break;
                        }
                        case 3: {
                            pageEvents = eventService.findEventOrderByPopular(pageable);
                            break;
                        }
                        default:
                            pageEvents = eventService.findEventOrderByEventName(pageable);
                    }
                }
            } else {
                switch (sort) {
                    case 1: {
                        pageEvents = eventService.findEventOrderByEventName(pageable);
                        break;
                    }
                    case 2: {
                        pageEvents = eventService.findEventOrderByBeginTime(pageable);
                        break;
                    }
                    case 3: {
                        pageEvents = eventService.findEventOrderByPopular(pageable);
                        break;
                    }
                    default:
                        pageEvents = eventService.findEventOrderByEventName(pageable);
                }
            }

            long totalItems = pageEvents.getTotalElements();
            int totalPages = pageEvents.getTotalPages();
            eventLists = pageEvents.getContent();
            if (eventLists.isEmpty()) {
                model.addAttribute("message", "Kh??ng c?? d??? li???u c?? s???n");
            } else {
                model.addAttribute("totalItems", totalItems);
                model.addAttribute("totalPages", totalPages);
                model.addAttribute("eventList", eventLists);
                model.addAttribute("pageNumber", pageNumber);
                model.addAttribute("myTotalPages", totalPages - 1);
            }
            model.addAttribute("eventList", eventLists);
            model.addAttribute("sort", sort);
            model.addAttribute("choice", choice);
            System.out.println("sort2=" + sort);
            System.out.println("choice=" + choice);
            System.out.println("keyword=" + keyword);
            model.addAttribute("keyword", keyword);
            return "/event/find_event_list";
        } catch (Exception e) {
            e.printStackTrace();
            return "500Page";
        }
    }


    @GetMapping("/create_event")
    public String showCreateEventForm(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            return "/event/create_event_host";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/update_host_event{id}")
    public String showUpdateEventForm(Model model, HttpSession session, @RequestParam("id") Long id) {
        if (session.getAttribute("user") != null) {
            Event e = eventService.getEventById(id);
            model.addAttribute("event", e);
            return "/event/update_event_host";
        } else {
            return "redirect:/login";
        }

    }

    //T???o m???i m???t s??? ki???n
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
        if ((session.getAttribute("user")) != null) {
            if (beginDate.after(endDate)) {
                model.addAttribute("msg", "Ng??y b???t ?????u tr??? h??n ng??y k???t th??c");
                return "event/create_event_host";
            }
            User user = (User) session.getAttribute("user");
            Long millis = System.currentTimeMillis();
            Date datePost = new Date(millis);
            Post post = new Post(eventName, content, datePost, user);
            String hostname = user.getLastName();
            Event event = new Event(eventName, location, agePermit, numOfMem, beginDate, endDate, hostname, donation, post);
            event.setStatus(new Status(1L, "Ch??a b???t ?????u"));
            post.setEvent(event);
            eventService.insertEvent(event);
            postService.insertPost(post);
            UserEvent ue = new UserEvent(event, user, "Host", true);
            UserEvent checkOne = userEventService.insertUserEvent(ue);
            if (checkOne != null) {
                System.out.println("Th??m m???i th??nh c??ng");
            }
            //    eventService.createEventByHost(user.getUserId(), event.getEventId(), "Host", true);
            System.out.println("eventId=" + event.getEventId());
            //  return "redirect:/"+ context.getContextPath()+"/my_event";
            return "redirect:/events/host_event";
        } else {
            System.out.println("Chua dang nhap");
            model.addAttribute("message", "B???n ch??a x??c th???c ????? th???c hi???n h??nh ?????ng n??y");
            return "/403Page";
        }
    }

    @PostMapping("/update_event")
    public String updateEvent(Model model, @RequestParam("eId") Long id, HttpSession session, @RequestParam("eventName") String eventName
            , @RequestParam("location") String location
            , @RequestParam("beginDate") Date beginDate
            , @RequestParam("endDate") Date endDate
            , @RequestParam("numOfMem") int numOfMem
            , @RequestParam("agePermit") int agePermit
            , @RequestParam("content") String content
    ) {
        if ((session.getAttribute("user")) != null) {
            User user = (User) session.getAttribute("user");
            Event event = eventService.getEventById(id);
            event.setEventName(eventName);
            event.setLocation(location);
            event.setBeginTime(beginDate);
            event.setEndTime(endDate);
            event.setNumOfMem(numOfMem);
            event.setAge(agePermit);
            event.getPost().setContent(content);
            event.setStatus(new Status(1L, "Ch??a b???t ?????u"));
            eventService.updateEvent(event);
            postService.updatePost(event.getPost());
            System.out.println("C???p nh???t th??nh c??ng");
            System.out.println("eventId=" + event.getEventId());
            return "redirect:/events/host_event";
        } else {
            System.out.println("Chua dang nhap");
            model.addAttribute("message", "B???n ch??a x??c th???c ????? th???c hi???n h??nh ?????ng n??y");
            return "/403Page";
        }
    }


    @GetMapping("/admin")
    public String showEventOnAdmin(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            model.addAttribute("myUser", u);
            List<Event> eventLists = eventService.getAllEvents(); // Maybe: L???i ch??? event ch??a duy???t
            model.addAttribute("Events", eventLists);
            return "admin/EventsManager";
        } else {
            return "403Page";
        }
    }

    @PostMapping("/admin")
    public String View(Model model, @RequestParam("eventName") String keyword, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            model.addAttribute("myUser", u);
            List<Event> listOfEvent = eventService.findEventByEventName(keyword);
            model.addAttribute("Events", listOfEvent);
            model.addAttribute("eventName", keyword);
            return "admin/EventsManager";
        } else {
            return "403Page";
        }
    }

    // 3.Ch???c n??ng x??a s??? ki???n:
    @GetMapping("/admin/delete/{id}")
    public String showEventOnAdmin(Model model, HttpSession session, @PathVariable("id") Long eventId) {
        if (session.getAttribute("user") != null) {
            System.out.println("x??a s??? ki???n " + eventId);
            eventService.deleteEvent(eventId);
            return "redirect:/events/admin";
        } else {
            return "403Page";
        }
    }


    //BachLT: Qu???n l?? danh s??ch ho???t ?????ng ??ang tham gia: trang ?????u ti??n c???a recruite volunteer=> my activity
    // Hi???n th??? trang host event v???i nh???ng s??? ki???n do b???n th??n user t??? ch???c
    @GetMapping("/host_event")
    public String showMyHostEvent(Model model, HttpSession session) {
        User user = null;
        int currentPage = 0;
        Pageable pageable = PageRequest.of(0, 10);
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
        if (user != null) {
            Page<Event> eventPages = eventService.findEventOfHost(user.getUserId(), "host", pageable);
            List<Event> eventList = eventPages.getContent();
            if (eventList.isEmpty()) {
                model.addAttribute("message", "Kh??ng c?? d??? li???u");
            } else {
                model.addAttribute("events", eventList);
            }
            model.addAttribute("user", user);
            return "event/host_event";
        } else {
            System.out.println("Ch??a ????ng nh???p");
            return "redirect:/login";
        }
    }

    // Show tat ca hoat dong dang tham gia hoac phe duyet:
    @GetMapping("/member_event")
    public String showWannaJoinEvent(Model model, HttpSession session) {
        //Dung session de lay user hien tai
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            List<UserEvent> userEventList = userEventService.findAllEventWithMember(u.getUserId(), "Member");
            /*Test
             * */
            if (userEventList.isEmpty()) {
                model.addAttribute("message", "Kh??ng c?? d??? li???u");
            } else {
                model.addAttribute("myEvents", userEventList);
            }
            model.addAttribute("user", u);
            return "event/member_event";
        } else {
            model.addAttribute("message", "B???n ch??a ????ng nh???p th??nh vi??n ????? v??o trang n??y");
            return "403Page";
        }
    }

    //--------------------------------------- K???t th??c ph???n My_Event-----------------------------

    //1 . Hi???n th??? danh s??ch th??nh vi??n trong qu???n l?? th??nh vi??n c???a host
    @GetMapping("/list_of_member/{eventId}")
    public String showAllMemberOfEvent(Model model, @PathVariable("eventId") Long eventId, HttpSession session) {

        if (session.getAttribute("user") != null) {
            List<UserEvent> memberList = userEventService.findAllMemberInEvent(eventId);
            Event myEvent = eventService.getEventById(eventId);
            model.addAttribute("event", myEvent);
            User host = userEventService.findHostInAEvent(memberList);
            model.addAttribute("host", host);
            User user = (User) session.getAttribute("user");
            model.addAttribute("isHost", user.getUserId().equals(host.getUserId()));
            System.out.println(user.getUserId().equals(host.getUserId()));
            if (memberList != null) {
                model.addAttribute("members", memberList);
            } else {
                model.addAttribute("message", "Kh??ng c?? d??? li???u");
            }
            return "event/list_of_member";
        } else {
            return "403Page";
        }
    }

    @GetMapping("/waiting_list/{eventId}")
    public String showWaitingVolunteerOfEvent(Model model, @PathVariable("eventId") Long eventId) {
        List<UserEvent> memberList = userEventService.findAllWaitingApproval(eventId);
        Event myEvent = eventService.getEventById(eventId);
        model.addAttribute("event", myEvent);
        if (memberList != null) {
            model.addAttribute("members", memberList);
        } else {
            model.addAttribute("message", "Kh??ng c?? d??? li???u");
        }
        return "event/waiting_approval";
    }

    //Ch???c n??ng: Ph?? duy???t th??nh vi??n.
    //1. Ch???p nh???n
    @PostMapping("/approval")
    public String approvalMember(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
        Event event = eventService.getEventById(eventId);
        // N???u ?????y:
        // N???u ko
        int curMem = event.getCurrentMem();
        if (curMem <= event.getNumOfMem()) {
            model.addAttribute("msg", "S??? l?????ng s??? ki???n ???? ?????y");
        } else {
            UserEvent userEvent = userEventService.findUserEventByUserAndEventId(eventId, userId);
            userEvent.setApproval(true);
            userEventService.updateUserEvent(userEvent);
            event.setCurrentMem(++curMem);
        }
        return "redirect:/events/waiting_list/" + eventId;
    }

    //2. T??? ch???i
    @PostMapping("/disapproval")
    public String disapprovalMember(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
        UserEvent userEvent = userEventService.findUserEventByUserAndEventId(eventId, userId);
        userEventService.deleteUserEvent(userEvent);
        // model.addAttribute()
        System.out.println("X??a kh???i danh s??ch ?????i");
        return "redirect:/events/waiting_list/" + eventId;
    }


    @GetMapping("/cancelRequest")
    public String cancelRequestJoin(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
        UserEvent member = userEventService.findUserEventByUserAndEventId(eventId, userId);
        boolean check = userEventService.deleteUserEvent(member);
        if (check) {
            model.addAttribute("message", "H???y th??nh c??ng");
            System.out.println("H???y th??nh c??ng");
        }
        return "redirect:/events/member_event";
    }

    // X??a ??i m???t th??nh vi??n trong t???i danh s??ch th??nh vi??n trong 1 s??? ki???n
    @GetMapping("/member/delete")
    public String removeAnMember(Model model, @RequestParam("eId") Long eId, @RequestParam("uId") Long uId, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            UserEvent memberDel = userEventService.findUserEventByUserAndEventId(eId, uId);
            boolean delete = userEventService.deleteUserEvent(memberDel);
            if (delete) {
                model.addAttribute("successMes", "X??a th??nh c??ng");
            } else {
                model.addAttribute("failMes", "X??a th???t b???i");
            }
            return "redirect:/events/list_of_member/" + eId;
        }
        return "redirect:/login";
    }

    // Danh s??ch c??c s??? ki???n ch??? ph?? duy???t
    @GetMapping("/admin/waiting_approval")
    public String waitingApprovalEvent(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().getRoleName().equals("ADMIN")) {
                model.addAttribute("myUser", u);
                List<Event> eventList = eventService.findDisapprovalList();
                model.addAttribute("eventList", eventList);
                //    model.addAttribute(eventName)
                return "/admin/event_waiting_approval";
            }
        }
        return "403Page";
    }

    @PostMapping("/admin/approval/search")
    public String searchDisapprovalList(Model model, @RequestParam("eventName") String keyword, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            model.addAttribute("myUser", u);
            List<Event> listOfEvent = eventService.findDisapprovalListByEventName(keyword);
            if(listOfEvent.isEmpty())
            {
                model.addAttribute("msg", "Kh??ng c?? d??? li???u");
            }else {
                model.addAttribute("eventList", listOfEvent);
                model.addAttribute("eventName", keyword);
            }
            return "admin/event_waiting_approval";
        } else {
            return "403Page";
        }
    }

    @PostMapping("/admin/approval/")
    public String acceptEventByAdmin(Model model, HttpSession session, @RequestParam("eId") Long eId) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().getRoleName().equals("ADMIN")) {
                Event e = eventService.getEventById(eId);
                e.setApproval(true);
                eventService.updateEvent(e);
                System.out.println("Duy???t th??nh c??ng");
                return "redirect:/events/admin/waiting_approval";
            }
        }
        return "403Page";
    }

    @PostMapping("/admin/disapproval/")
    public String declineEventByAdmin(Model model, HttpSession session, @RequestParam("eId") Long eId) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            if (u.getRole().getRoleName().equals("ADMIN")) {
                eventService.deleteEvent(eId);
                System.out.println("T??? ch???i th??nh c??ng");
                return "redirect:/events/admin/waiting_approval";
            }
        }
        return "403Page";
    }
}

// H??m hi???n th??? find_event ch??nh
//  @GetMapping("/page{pageNumber}")
//    public String pagingEventWithSort(Model model, @RequestParam(value = "sort", required = false) Integer sort,
//                                      @PathVariable(value = "pageNumber", required = false) Integer pageNumber) {
//        try {
//            int pageSize = 10;
//            if (pageNumber == null) pageNumber = 0;// check n???u nh?? trang m???c ?????nh th?? m??nh s??? g??n page hi???n t???i l?? 0
//
//            Pageable pageable = PageRequest.of(pageNumber, 10);// T???o m???t lo???i ph??n trang v???i pageNumber l?? v??? tr?? trang v?? size l?? s??? ph???n t???.
//            Page<Event> eventPages;
//            if (sort != null) {
//                switch (sort) {
//                    case 1:
//                    default:
//                        eventPages = eventService.findEventWithPagination(pageNumber, pageSize);
//                }
//            } else {
//                eventPages = eventService.findEventWithPagination(pageNumber, pageSize);
//            }
//
//            long totalItems = eventPages.getTotalElements();
//            int totalPages = eventPages.getTotalPages();
//            List<Event> eventLists = eventPages.getContent();
//            if (eventLists.isEmpty()) {
//                model.addAttribute("message", "Kh??ng c?? d??? li???u c?? s???n");
//            } else {
//                model.addAttribute("totalItems", totalItems);
//                model.addAttribute("totalPages", totalPages);
//                model.addAttribute("eventList", eventLists);
//                model.addAttribute("pageNumber", pageNumber);
//                model.addAttribute("myTotalPages", totalPages - 1);
//            }
//            return "/event/find_event_list";
//        } catch (NullPointerException e) {
//            System.err.println("L???i kh??ng c?? d??? li???u khi load trang");
//            e.printStackTrace();
//            return "500Page";
//        }
//    }

//    @GetMapping("/page{pageNumber}")
//    public String pagingEventPage(Model model, @PathVariable("pageNumber") int pageNumber) {
//        int pageSize = 10;
//        Page<Event> eventPages = eventService.findEventWithPagination(pageNumber, pageSize);
//        long totalItems = eventPages.getTotalElements();
//        int totalPages = eventPages.getTotalPages();
//        List<Event> eventLists = eventPages.getContent();
//        model.addAttribute("totalItems", totalItems);
//        model.addAttribute("myTotalPages", totalPages - 1);
//        model.addAttribute("eventList", eventLists);
//        model.addAttribute("pageNumber", pageNumber);
//        return "/event/find_event_list";
//    }

//    @GetMapping("/find?l={location}&k={keyword}/{pageNumber}")
//    public String showAllEventsByFind(Model model, @RequestParam("location") String location,
//                                      @RequestParam("keyword") String keyword, @PathVariable("pageNumber") int pageNumber) {
//        Page<Event> eventPages;
//        List<Event> eventLists;
//        if (location != null || keyword != null) {
//            eventLists = eventService.findEventByLocationAndKeyword(location, keyword);
//        } else {
//            eventLists = eventService.getAllEvents();
//        }
//
//        model.addAttribute("eventList", eventLists);
//        model.addAttribute("location", location);
//        //     model.addAttribute("keyword", keyword);
//        return "/event/find_event_list";
//    }