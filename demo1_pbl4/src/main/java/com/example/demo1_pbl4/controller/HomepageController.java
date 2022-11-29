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
        return "index";
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
   // @GetMapping("/register")

    //url
    @RequestMapping(value="/register",method ={RequestMethod.GET, RequestMethod.POST} )
    public String goRegister(Model model)
    {
        model.addAttribute("myUser",new User());
        return "register1";
    }
    @PostMapping("/process_register") // action form
    public String registerAccount(@ModelAttribute("myUser")User user)
    {
        userService.insertUser(user);
        return "user_list";
    }
}
