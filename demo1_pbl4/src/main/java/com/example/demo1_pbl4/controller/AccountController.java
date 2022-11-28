package com.example.demo1_pbl4.controller;


import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.UserService;
import com.example.demo1_pbl4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/home")
public class AccountController {
    @Autowired
    private UserService userSer;


    @GetMapping("")
    public String viewHomePage() {
        return  "index";
    }

    @RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
    public String showRegisterForm(Model model) {

        model.addAttribute("myUser", new User());
        return "register";
    }

    @RequestMapping(value = "/process_register", method = { RequestMethod.GET, RequestMethod.POST })
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return "process_register";
        //userRepo.save(user); luu user
    }
}

