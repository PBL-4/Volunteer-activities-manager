package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/get{postId}")
    public String showPostById(Model model, @PathVariable ("postId") Long id)
    {

        return "/post/show_post";
    }
}
