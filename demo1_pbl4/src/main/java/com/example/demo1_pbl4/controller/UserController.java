package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired// While running cannot import
    private UserService userService;

    public UserController(UserService userService) {
        super();//?
        this.userService = userService;
    }

    @GetMapping("")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";
    }

    @GetMapping("/insertForm")
    public String showCreateForm(Model model) {
        User user = new User();
        model.addAttribute("myUser", user);
        return "insert_user";
    }

    @PostMapping("/insertUser")
    // Voi bien se dung @RequestParam nhung voi doi tuong thi
    public ModelAndView createUser(@ModelAttribute("myUser") User myUser) {
        if (myUser != null ) {
            userService.insertUser(myUser);
        }       // Chỗ này cần phải tối ưu.

            return new ModelAndView("redirect:/users/");
        }
    }

