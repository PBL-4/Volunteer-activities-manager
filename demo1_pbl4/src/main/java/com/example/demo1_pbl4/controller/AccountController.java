package com.example.demo1_pbl4.controller;


import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.UserService;
import com.example.demo1_pbl4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;


    @GetMapping("")
    public String viewHomePage() {
        return  "index";
    }

    @RequestMapping(value="/register",method ={RequestMethod.GET, RequestMethod.POST} )
    public String goRegister(Model model)
    {
        model.addAttribute("User",new User());
        return "register1";
    }
    @RequestMapping(value="/login",method ={RequestMethod.GET, RequestMethod.POST} )
    public String goLogin(Model model)
    {
        model.addAttribute("User",new User());
        return "login";
    }
    @RequestMapping(value = "/process_register", method = { RequestMethod.GET, RequestMethod.POST })
    public String processRegister(@ModelAttribute("User") User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Password cua toi: " + user.getPassword());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User temp = userService.insertUser(user);
        if(temp != null) {
            System.out.println("Hello bug 1");
            return "process_register";
        }
        else
        {
            System.out.println("Hello bug 2");
            return "redirect:/register";
        }
        //userRepo.save(user); luu user
    }
    @ModelAttribute("User")
    public User getUser(HttpServletRequest request)
    {
       User registerUser = (User) request.getAttribute("User");
       return registerUser;
    }

}

