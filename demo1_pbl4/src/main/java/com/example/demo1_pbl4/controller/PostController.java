package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Post;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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


    private DonateService donateService;
    public PostController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("")
    public ModelAndView showAllPost() {
        return new ModelAndView("/post/post_list", "posts", postService.getAllPosts());
    }

    @GetMapping("/get{id}")         // ko co dau .  , dau ? thanh %20
    public String showPostById(Model model, @RequestParam("id") Long id, HttpSession session) // Tai sao lai dung @requestParam
    {
        Long userId = Long.valueOf(-1);
        User user = null;
        if (session.getAttribute("user") != null)
        {
            user = (User)session.getAttribute("user");
            userId = user.getUserId();
        }
        Post post=postService.getPostById(id);
        model.addAttribute("post",post);
        model.addAttribute("comments",commentService.getAllComments());
        model.addAttribute("total",commentService.countComment());
        model.addAttribute("Rating_Event",new Rating());
        Date date = new Date(System.currentTimeMillis());
        int timeCompare = post.getEvent().getEndTime().compareTo(date);
        model.addAttribute("Eventend",timeCompare);
        //
        UserEvent ue = userEventService.findUserEventByUserAndEventId(id, userId);
        if(ue == null)
        {
            model.addAttribute("IamMember", 0);
        } else
        {
            model.addAttribute("IamMember", 1);
        }

        return "post/post_of_event";
    }
    @PostMapping("/saverating")
    public String processRating(@ModelAttribute(value="Rating_Event") Rating rating_event, HttpSession session, @RequestParam("id") Long eventId)
    {
        Long userId = Long.valueOf(-1);
        User user = null;
        if (session.getAttribute("user") != null)
        {
            user = (User)session.getAttribute("user");
            userId = user.getUserId();
        }
        UserEvent ue = userEventService.findUserEventByUserAndEventId(eventId, userId);
        Rating re = ratingService.findRatingByUserEventId(eventId,userId);
        if(re == null)
        {
            if(userId != -1)
            {
                rating_event.setUserEvent(ue);
                ratingService.insertRating(rating_event);
            }
        }
        else
        {
            re.setPoint1(rating_event.getPoint1());
            re.setPoint2(rating_event.getPoint2());
            re.setPoint3(rating_event.getPoint3());

            ratingService.insertRating(re);
        }
        return "redirect:/posts/get?id=" + eventId.toString();
    }

    @GetMapping("/donation")
    public String ShowDonation(Model model, HttpSession session, @RequestParam("postId") Long id) {
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            model.addAttribute("postId", id);
            model.addAttribute("userId",user.getUserId());
            return "post/DonationVolunteer";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/donation")
    public String ShowBach(Model model,HttpSession session, @RequestParam("donation") double donation, @RequestParam("postId") Long id, @RequestParam("userId") Long userId) {
        Post post = postService.getPostById(id);
        Event event = post.getEvent();
        double quy = event.getDonation() + donation;
        event.setDonation(quy);
        eventService.updateEvent(event);

        //donate
        Donate donate = new Donate();
        donate.setUser((User) session.getAttribute("user"));
        donate.setEvent(event);
        donate.setMoney(donation);
        donate.setDonateDate(new Date(System.currentTimeMillis()));
        donateService.updateDonate(donate);


        return "redirect:/posts/get?id=" + id;


    }
}

