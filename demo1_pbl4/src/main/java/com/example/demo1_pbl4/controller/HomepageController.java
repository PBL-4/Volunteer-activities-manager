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
import org.thymeleaf.context.IContext;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomepageController {
    @Autowired
    private UserService userService;

    //    @Autowired
//    private CustomUserDetailsService customUserDetailsService; // Dung de phan quyen trong spring boot
    //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private B5EncodePassword b5EncodePassword = new B5EncodePassword();


    @GetMapping(value = {"/", "home"})
    public String showHomepage() {
        return "homepage/homepage";
    }

    @GetMapping("/login")
    public String goLogin(Model model) {
        //Dungf HttpServletRequest khong lay name dc
        //   Cookie[] cookies = request.getCookies();
        //   username=cookies[0].getValue();
        //   password=cookies[1].getValue();
        // @CookieValue("username") String username, @CookieValue("password") String password
//        if (username != null && password != null) {
//
//            model.addAttribute("username", username);
//            model.addAttribute("password", password);
//        }
        return "homepage/login_form";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String goRegister(Model model) {
        model.addAttribute("myUser", new User());
        return "homepage/register_form";
    }

    @GetMapping("/admin_home")
    public String goAdminHome() {
        return "admin/admin";
    }

//    @GetMapping("/process_login") // action form
//    public String showLoginSuccess(Model model, HttpSession session, @RequestParam("username")String username,@RequestParam("password")String password) {
//        //   session.setAttribute("username", username);
//        //  customUserDetailsService.loadUserByUsername()
//       // CustomUserDetails customUserDetails=new CustomUserDetails()
//        return "redirect:/";
//    }

    @PostMapping("/process_login") // action form
    public String loginAccount(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {

        session.setAttribute("username", username);

        if (userService.checkLogin(username, password)) {
            System.out.println("Dang nhap thanh cong");
            //  session.setAttribute("username", username);
            session.setAttribute("user", userService.findUserByUsername(username));
            //  System.out.println(context.getContextPath());
            // return "redirect:/"+context.getContextPath();
            Cookie cookie1 = new Cookie("username", username);
            cookie1.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            Cookie cookie2 = new Cookie("password", password);
            cookie2.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            response.addCookie(cookie1);
            response.addCookie(cookie2);
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


    @GetMapping("/history_donation")
    public String showHistoryDonation(HttpSession session) {
        return "user/history_donation";
    }


    @GetMapping("logout")

    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }
}
