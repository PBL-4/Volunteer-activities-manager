package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PostController {
    @Autowired
    private PostService postService;
}
