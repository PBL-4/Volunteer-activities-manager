package com.example.demo1_pbl4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {
    @GetMapping("/")
    public String showHomepage()
    {
        return "index";
    }
    @GetMapping("/login")
    public String goLogin()
    {
        return "login";
    }
    @GetMapping("/register")
    public String goRegister()
    {
        return "register";
    }
}
