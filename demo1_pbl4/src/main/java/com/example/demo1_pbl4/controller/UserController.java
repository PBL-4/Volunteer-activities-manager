package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        if (myUser != null) {
            userService.insertUser(myUser);
        }       // Chỗ này cần phải tối ưu.

        return new ModelAndView("redirect:/users/");
    }

    @GetMapping("/admin")
    public String showUserOnAdmin(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/UserManager";
    }

    @PostMapping("/admin")
    public String View(Model model, @RequestParam("keyword") String keyword) {
        List<User> listuser = userService.search(keyword);
        model.addAttribute("users", listuser);
        model.addAttribute("keyword", keyword);

        return "admin/UserManager";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/admin";
    }

    @GetMapping("/mem_of_event/{eventId}")
    public String showAllMemOfEvent(Model model,@PathVariable ("eventId")Long eventId)
    {
        List<MemberInRating> members=userService.findMemberInEvent(eventId,"Member");
       // System.out.println("member="+members);
        model.addAttribute("members",members);
        return "rating/rate_all_member";
    }
}






