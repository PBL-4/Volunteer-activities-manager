package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.*;

import com.example.demo1_pbl4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingEventService ratingService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserEventService userEventService;

    @Autowired
    private DonateService donateService;

    public PostController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("")
    public ModelAndView showAllPost() {
        return new ModelAndView("/post/post_list", "posts", postService.getAllPosts());
    }

    @GetMapping("/get{id}")         // ko co dau .  , dau space thanh %20
    public String showPostById(Model model, @RequestParam("id") Long id, HttpSession session) // Tai sao lai dung @requestParam
    {
        try {
            // Long userId = Long.valueOf(-1);
            User user = null;
            if (session.getAttribute("user") != null) {
                user = (User) session.getAttribute("user");
            }
//            Post post = postService.getPostById(id);
//            Event event = post.getEvent();
            Event event = eventService.getEventById(id);
            Post post = event.getPost();
            model.addAttribute("post", post);
            model.addAttribute("myComment", new Comment());
            model.addAttribute("comments", commentService.getAllCommentsByPost(id));
            model.addAttribute("total", commentService.countCommentByPost(id));
            model.addAttribute("Rating_Event", new Rating());
            //BachLT
            model.addAttribute("user", user);
            model.addAttribute("event", post.getEvent());
            Date date = new Date(System.currentTimeMillis());
            boolean enableJoin = false;
            String reason = null;
            boolean isFull = event.getCurrentMem() >= event.getNumOfMem();
            boolean isMember = false;
           boolean isEndDateEvent=false;
            UserEvent ue = null;
            if (user != null) {
                ue = userEventService.findUserEventByUserAndEventId(event.getEventId(), user.getUserId());
                if (ue != null && ue.getApproval()) {
                    isMember = true;
                }
            }
            if (event.getBeginTime().after(date)) {
                enableJoin = true;
                if (isFull) {
                    reason = "Sự kiện đã đầy";
                    enableJoin = false;
                }
                if (isMember) {
                    reason = "Đã tham gia";
                    enableJoin = false;
                }
            } else if (event.getBeginTime().before(date) && event.getEndTime().after(date)) {
                reason = "Sự kiện đang diễn ra";
                enableJoin = false;
            } else if (event.getEndTime().before(date)) {
                reason = "Sự kiện đã kết thúc";
                isEndDateEvent=true;
                enableJoin = false;
            } else {
                enableJoin = false;
            }
//            model.addAttribute("endEvent", timeCompare);
            //
            model.addAttribute("enableJoin", enableJoin);
            model.addAttribute("reason", reason);
            model.addAttribute("isMember", isMember);
            model.addAttribute("isEndDateEvent",isEndDateEvent);
            //Kiem tra danh gia vao bai post va danh gia sao cung voi diem trung binh:
            Long eId = post.getEvent().getEventId();
            Event e = eventService.getEventById(eId);
            if (eventService.isFinishEvent(eId)) {
                ratingService.setStarEachEvent(eId);
                model.addAttribute("hasRating", true);
                model.addAttribute("star", e.getStar());
                System.out.println(e.getStar());
                model.addAttribute("avgPoint", e.getRating());
                System.out.println(e.getRating());
            } else {
                model.addAttribute("hasRating", false);
            }
            model.addAttribute("currentMem", e.getCurrentMem());
            model.addAttribute("numOfMem", e.getNumOfMem());
            return "post/post_of_event";

        } catch (NullPointerException e) {
            return "500Page";
        } catch (
                NoSuchElementException e) {
            return "404Page";
        }

    }

    @PostMapping("/saverating")
    public String processRating(@ModelAttribute(value = "Rating_Event") Rating rating_event, HttpSession session, @RequestParam("id") Long eventId) {
        Long userId = Long.valueOf(-1);
        User user = null;
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
            userId = user.getUserId();
        }
        UserEvent ue = userEventService.findUserEventByUserAndEventId(eventId, userId);
        Rating re = ratingService.findRatingByUserEventId(eventId, userId);
        if (re == null) {
            if (userId != -1) {
                rating_event.setUserEvent(ue);
                ratingService.insertRating(rating_event);
            }
        } else {
            re.setPoint1(rating_event.getPoint1());
            re.setPoint2(rating_event.getPoint2());
            re.setPoint3(rating_event.getPoint3());

            ratingService.insertRating(re);
        }
        return "redirect:/posts/get?id=" + eventId.toString();
    }

    @GetMapping("/donation")
    public String showDonationForm(Model model, HttpSession session, @RequestParam("postId") Long id) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            Post post = postService.getPostById(id);
            model.addAttribute("post", post);
            model.addAttribute("userId", user.getUserId());
            return "post/donation_volunteer";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/donation")
    public String processDonation(Model model, HttpSession session, @RequestParam("donation") double donation, @RequestParam("postId") Long id, @RequestParam("userId") Long userId) {
        Post post = postService.getPostById(id);
        Event event = post.getEvent();
        double quy = event.getFund() + donation;
        event.setFund(quy);
        eventService.updateEvent(event);
        System.out.println(id);
        //donate
        Donate donate = new Donate();
        System.out.println(donation);
        donate.setUser((User) session.getAttribute("user"));
        donate.setEvent(event);
        donate.setMoney(donation);
        donate.setDonateDate(new Date(System.currentTimeMillis()));
        if (donateService.createDonate(donate)) {
            System.out.println(donateService.createDonate(donate));
        }
        return "redirect:/posts/get?id=" + id;
    }

    // xu ly send request
    @PostMapping("/sendRequest")
    public String processJoin(Model model, @RequestParam(value = "userId", required = false) Long userId, @RequestParam("eventId") Long eventId,
                              @RequestParam("personalInfo") String personalInfo, @RequestParam("skill") String skill) {
        if (userId != null) {
            UserEvent userEvent = new UserEvent();
            User user = userService.getUserById(userId);
            System.out.println("userId" + user.getUserId());

            userEvent.setUser(user);
            Event event = eventService.getEventById(eventId);
            System.out.println("eventId" + event.getEventId());
            userEvent.setEvent(event);
            userEvent.setApproval(false);
            userEvent.setInfoOfMem(personalInfo);
            userEvent.setSkill(skill);
            userEvent.setEventRole("Member");
            userEvent.setUserEventId(new UserEventId(userId, eventId));
            userEventService.insertUserEvent(userEvent);// Tạo khóa chính
            return "redirect:/posts/get?id=" + eventId;
        } else {
            model.addAttribute("unLogin", "Bạn cần đăng nhập thì mới tham gia được");
            return "homepage/login_form";
        }

    }
}