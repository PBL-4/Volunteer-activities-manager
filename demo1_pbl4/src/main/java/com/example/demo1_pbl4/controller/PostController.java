package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.Rating_Event;
import com.example.demo1_pbl4.service.CommentService;
import com.example.demo1_pbl4.service.PostService;
import com.example.demo1_pbl4.service.RatingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingEventService ratingService;

    public PostController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping("")
    public ModelAndView showAllPost(){
        return new ModelAndView("/post/post_list","posts",postService.getAllPosts());
    }

    @GetMapping("/get{id}")         // ko co dau .  , dau ? thanh %20
    public String showPostById(Model model, @RequestParam("id") Long id) // Tai sao lai dung @requestParam
    {
        Post post=postService.getPostById(id);
        model.addAttribute("post",post);
        model.addAttribute("comments",commentService.getAllComments());
        model.addAttribute("total",commentService.countComment());
        return "post/post_of_event";
    }
    @PostMapping("/saverating")
    public String processRating(@ModelAttribute(value="Rating_Event")Rating_Event rating_event)
    {

        return "post/post_of_event";
    }
}
