package com.example.demo1_pbl4.controller;

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

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingEventService ratingService;
    @Autowired
    private UserService userService;

    @Autowired
    private UserEventService userEventService;

    public PostController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("")
    public ModelAndView showAllPost(){
        return new ModelAndView("/post/post_list","posts",postService.getAllPosts());
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
}