package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Role;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.TotalDonationOfUser;
import com.example.demo1_pbl4.security.CustomUserDetailsService;
import com.example.demo1_pbl4.service.DonateService;
import com.example.demo1_pbl4.service.EventService;
import com.example.demo1_pbl4.service.ReportService;
import com.example.demo1_pbl4.service.UserService;
import com.example.demo1_pbl4.utils.B5EncodePassword;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.Calendar;
import java.util.List;

@Controller
public class HomepageController {
    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private DonateService donateService;

    @Autowired
    private ReportService reportService;

    //    @Autowired
//    private CustomUserDetailsService customUserDetailsService; // Dung de phan quyen trong spring boot
    //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private B5EncodePassword b5EncodePassword = new B5EncodePassword();


    @GetMapping(value = {"/", "home"})

    public String showHomepage() {
        return "homepage/homepage";
    }

    @GetMapping("/login")
    public String goLogin(Model model, @CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password) {
        //Dungf HttpServletRequest khong lay name dc
        //   Cookie[] cookies = request.getCookies();
        //   username=cookies[0].getValue();
        //   password=cookies[1].getValue();

        if (username != null && password != null) {

            model.addAttribute("username", username);
            model.addAttribute("password", password);
        }
        return "homepage/login_form";
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String goRegister(Model model) {
        model.addAttribute("myUser", new User());
        return "homepage/register_form";
    }

    @PostMapping("/process_login") // action form
    public String loginAccount(Model model, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam(value = "rememberMe", required = false) Boolean remember, HttpServletResponse response, HttpServletRequest request) {


        session.setAttribute("username", username);

        if (userService.checkLogin(username, password)) {
            System.out.println("Dang nhap thanh cong");
            //  session.setAttribute("username", username);
            session.setAttribute("user", userService.findUserByUsername(username));

            //  System.out.println(context.getContextPath());
            // return "redirect:/"+context.getContextPath();
            System.out.println(remember);
            if (remember != null) {
                Cookie cookie1 = new Cookie("username", username);
                cookie1.setMaxAge(5); // expires in 5mins
                Cookie cookie2 = new Cookie("password", password);
                cookie2.setMaxAge(5); // expires in 5mins
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            String url = request.getRequestURL().toString();
            // request.getServletPath();
            System.out.println("url: " + url);
            System.out.println("url1: " + request.getServletPath());
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


    @GetMapping("/history_donation")
    public String showHistoryDonation(HttpSession session) {
        return "user/history_donation";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
//        session.setAttribute("user", null); ,HttpSession session
//        return "redirect:/";
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/admin_home")
    public String showMyAdminHome(Model model, HttpSession session) {
        try {
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                if (user.getRole().getRoleName().equals("ADMIN")) {
                    model.addAttribute("myUser", user);
                    int y = Calendar.getInstance().get(Calendar.YEAR);
                    List<TotalDonationOfUser> topUsers = donateService.sortByTotalDonate();
                    List<Event> topEvents = eventService.sortEventByRating();
                    if (topEvents.size() >= 3) {
//                        model.addAttribute("topNotFound", "No data");
                        topEvents = topEvents.subList(0, 3);
                    }
                    if (topUsers.size() >= 3) {
                        topUsers = topUsers.subList(0, 3);
                    }
                    model.addAttribute("totalDonates", donateService.sumAllDonate());
                    model.addAttribute("totalUsers", userService.countAllUser());
                    model.addAttribute("totalEvents", eventService.countAllEvents());
                    model.addAttribute("topUsers", topUsers);
                    model.addAttribute("topEvents", topEvents);
                    model.addAttribute("numEvents", reportService.getEventPerMByY(y));
                    model.addAttribute("numUsers", reportService.getUserPerMByY(y));
                    model.addAttribute("numDonation", reportService.getDonatePerMByY(y));
                    return "/admin/admin_home";
                } else {
                    return "403Page";
                }
            } else {
                return "redirect:/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "500Page";
        }
    }
}


