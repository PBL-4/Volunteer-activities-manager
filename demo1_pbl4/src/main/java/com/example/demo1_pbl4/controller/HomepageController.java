package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomepageController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String showHomepage()
    {
        return "homepage/homepage";
    }
    @GetMapping("/login")
    public String goLogin()
    {
        return "login";
    }

    @GetMapping("/admin_home")
    public String goAdminHome()
    {
        return "admin";
    }

    @GetMapping("/post")
    public String goPostView()
    {
        return "post_of_event";
    }
   // @GetMapping("/register")

    //url

}
