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
                model.addAttribute("message", "Không có dữ liệu có sẵn");
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
                model.addAttribute("message", "Không có dữ liệu có sẵn");
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

    //Tạo mới một sự kiện
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
                model.addAttribute("msg", "Ngày bắt đầu trễ hơn ngày kết thúc");
                return "event/create_event_host";
            }
            User user = (User) session.getAttribute("user");
            Long millis = System.currentTimeMillis();
            Date datePost = new Date(millis);
            Post post = new Post(eventName, content, datePost, user);
            String hostname = user.getLastName();
            Event event = new Event(eventName, location, agePermit, numOfMem, beginDate, endDate, hostname, donation, post);
            event.setStatus(new Status(1L, "Chưa bắt đầu"));
            post.setEvent(event);
            eventService.insertEvent(event);
            postService.insertPost(post);
            UserEvent ue = new UserEvent(event, user, "Host", true);
            UserEvent checkOne = userEventService.insertUserEvent(ue);
            if (checkOne != null) {
                System.out.println("Thêm mới thành công");
            }
            //    eventService.createEventByHost(user.getUserId(), event.getEventId(), "Host", true);
            System.out.println("eventId=" + event.getEventId());
            //  return "redirect:/"+ context.getContextPath()+"/my_event";
            return "redirect:/events/host_event";
        } else {
            System.out.println("Chua dang nhap");
            model.addAttribute("message", "Bạn chưa xác thực để thực hiện hành động này");
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
            event.setStatus(new Status(1L, "Chưa bắt đầu"));
            eventService.updateEvent(event);
            postService.updatePost(event.getPost());
            System.out.println("Cập nhật thành công");
            System.out.println("eventId=" + event.getEventId());
            return "redirect:/events/host_event";
        } else {
            System.out.println("Chua dang nhap");
            model.addAttribute("message", "Bạn chưa xác thực để thực hiện hành động này");
            return "/403Page";
        }
    }


    @GetMapping("/admin")
    public String showEventOnAdmin(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            model.addAttribute("myUser", u);
            List<Event> eventLists = eventService.getAllEvents(); // Maybe: Lỗi chỗ event chưa duyệt
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

    // 3.Chức năng xóa sự kiện:
    @GetMapping("/admin/delete/{id}")
    public String showEventOnAdmin(Model model, HttpSession session, @PathVariable("id") Long eventId) {
        if (session.getAttribute("user") != null) {
            System.out.println("xóa sự kiện " + eventId);
            eventService.deleteEvent(eventId);
            return "redirect:/events/admin";
        } else {
            return "403Page";
        }
    }


    //BachLT: Quản lý danh sách hoạt động đang tham gia: trang đầu tiên của recruite volunteer=> my activity
    // Hiển thị trang host event với những sự kiện do bản thân user tổ chức
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
                model.addAttribute("message", "Không có dữ liệu");
            } else {
                model.addAttribute("events", eventList);
            }
            model.addAttribute("user", user);
            return "event/host_event";
        } else {
            System.out.println("Chưa đăng nhập");
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
                model.addAttribute("message", "Không có dữ liệu");
            } else {
                model.addAttribute("myEvents", userEventList);
            }
            model.addAttribute("user", u);
            return "event/member_event";
        } else {
            model.addAttribute("message", "Bạn chưa đăng nhập thành viên để vào trang này");
            return "403Page";
        }
    }

    //--------------------------------------- Kết thúc phần My_Event-----------------------------

    //1 . Hiển thị danh sách thành viên trong quản lý thành viên của host
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
                model.addAttribute("message", "Không có dữ liệu");
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
            model.addAttribute("message", "Không có dữ liệu");
        }
        return "event/waiting_approval";
    }

    //Chức năng: Phê duyệt thành viên.
    //1. Chấp nhận
    @PostMapping("/approval")
    public String approvalMember(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
        Event event = eventService.getEventById(eventId);
        // Nếu đầy:
        // NẾu ko
        int curMem = event.getCurrentMem();
        if (curMem <= event.getNumOfMem()) {
            model.addAttribute("msg", "Số lượng sự kiện đã đầy");
        } else {
            UserEvent userEvent = userEventService.findUserEventByUserAndEventId(eventId, userId);
            userEvent.setApproval(true);
            userEventService.updateUserEvent(userEvent);
            event.setCurrentMem(++curMem);
        }
        return "redirect:/events/waiting_list/" + eventId;
    }

    //2. Từ chối
    @PostMapping("/disapproval")
    public String disapprovalMember(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
        UserEvent userEvent = userEventService.findUserEventByUserAndEventId(eventId, userId);
        userEventService.deleteUserEvent(userEvent);
        // model.addAttribute()
        System.out.println("Xóa khỏi danh sách đợi");
        return "redirect:/events/waiting_list/" + eventId;
    }


    @GetMapping("/cancelRequest")
    public String cancelRequestJoin(Model model, @RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
        UserEvent member = userEventService.findUserEventByUserAndEventId(eventId, userId);
        boolean check = userEventService.deleteUserEvent(member);
        if (check) {
            model.addAttribute("message", "Hủy thành công");
            System.out.println("Hủy thành công");
        }
        return "redirect:/events/member_event";
    }

    // Xóa đi một thành viên trong tại danh sách thành viên trong 1 sự kiện
    @GetMapping("/member/delete")
    public String removeAnMember(Model model, @RequestParam("eId") Long eId, @RequestParam("uId") Long uId, HttpSession session) {
        if (session.getAttribute("user") != null) {
            User u = (User) session.getAttribute("user");
            UserEvent memberDel = userEventService.findUserEventByUserAndEventId(eId, uId);
            boolean delete = userEventService.deleteUserEvent(memberDel);
            if (delete) {
                model.addAttribute("successMes", "Xóa thành công");
            } else {
                model.addAttribute("failMes", "Xóa thất bại");
            }
            return "redirect:/events/list_of_member/" + eId;
        }
        return "redirect:/login";
    }

    // Danh sách các sự kiện chờ phê duyệt
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
                model.addAttribute("msg", "Không có dữ liệu");
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
                System.out.println("Duyệt thành công");
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
                System.out.println("Từ chối thành công");
                return "redirect:/events/admin/waiting_approval";
            }
        }
        return "403Page";
    }
}

// Hàm hiển thị find_event chính
//  @GetMapping("/page{pageNumber}")
//    public String pagingEventWithSort(Model model, @RequestParam(value = "sort", required = false) Integer sort,
//                                      @PathVariable(value = "pageNumber", required = false) Integer pageNumber) {
//        try {
//            int pageSize = 10;
//            if (pageNumber == null) pageNumber = 0;// check nếu như trang mặc định thì mình sẽ gán page hiện tại là 0
//
//            Pageable pageable = PageRequest.of(pageNumber, 10);// Tạo một loại phân trang với pageNumber là vị trí trang và size là số phần tử.
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
//                model.addAttribute("message", "Không có dữ liệu có sẵn");
//            } else {
//                model.addAttribute("totalItems", totalItems);
//                model.addAttribute("totalPages", totalPages);
//                model.addAttribute("eventList", eventLists);
//                model.addAttribute("pageNumber", pageNumber);
//                model.addAttribute("myTotalPages", totalPages - 1);
//            }
//            return "/event/find_event_list";
//        } catch (NullPointerException e) {
//            System.err.println("Lỗi không có dữ liệu khi load trang");
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