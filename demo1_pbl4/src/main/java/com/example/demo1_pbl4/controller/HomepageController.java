package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Role;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.security.CustomUserDetailsService;
import com.example.demo1_pbl4.service.UserService;
import com.example.demo1_pbl4.utils.B5EncodePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomepageController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private B5EncodePassword b5EncodePassword = new B5EncodePassword();

    @GetMapping("/")
    public String showHomepage() {
        return "homepage/homepage";
    }

    @GetMapping("/login")
    public String goLogin() {
        return "homepage/login_form";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String goRegister(Model model) {
        model.addAttribute("myUser", new User());
        return "homepage/register_form";
    }

    @GetMapping("/admin_home")
    public String goAdminHome() {
        return "admin";
    }

//    @GetMapping("/process_login") // action form
//    public String showLoginSuccess(Model model, HttpSession session, @RequestParam("username")String username,@RequestParam("password")String password) {
//        //   session.setAttribute("username", username);
//        //  customUserDetailsService.loadUserByUsername()
//       // CustomUserDetails customUserDetails=new CustomUserDetails()
//        return "redirect:/";
//    }

    @PostMapping("/process_login") // action form
    public String loginAccount(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) {
        session.setAttribute("username", username);
        //   UserDetails userDetails = customUserDetailsService.loadUserByUsername(principal.getName());
        //   session.setAttribute("username",userDetails.getUsername());
        //  String encodePass=b5EncodePassword.encodePass(password);
        if (userService.checkLogin(username, password)) {
            System.out.println("Dang nhap thanh cong");
            session.setAttribute("user", userService.findUserByUsername(username));
            return "redirect:/home";
        } else {
            //      System.out.println("Dang nhap that bai"+encodePass);
            model.addAttribute("failMessage", "Tai khoan hoac mat khau ko chinh xac");
            return "/homepage/login_form";
        }
    }


    @PostMapping("/process_register") // action form
    public String registerAccount(@ModelAttribute("myUser") User user) {
        Role role = new Role();
        user.setRole(role);
        String rawPassword = user.getPassword();
        //   String encodePass = b5EncodePassword.encodePass(rawPassword);
        user.setPassword(rawPassword);
        userService.insertUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "/403Page";
    }

    @GetMapping("/sponsor")
    public String showSponsorPage() {
        return "sponsor/sponsor";
    }

}
