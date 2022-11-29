package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public ModelAndView showAllPost(){
        return new ModelAndView("/post/post_list","posts",postService.getAllPosts());
    }

    @GetMapping("/get{id}") // ko co dau .  , dau ? thanh %20
    public String showPostById(Model model, @RequestParam("id") Long id) // Tai sao lai dung @requestParam
    {
        Post post=postService.getPostById(id);
        System.out.println("Hello");
        model.addAttribute("post",post);
        return "/post/post_each_event";
    }
}
