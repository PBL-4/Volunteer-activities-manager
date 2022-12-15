package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.DonateService;
import com.example.demo1_pbl4.service.UserEventService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/donate")
public class DonateController {
    @Autowired
    private DonateService donateService;
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showAllDonate(Model model,@RequestParam("userId")Long id,HttpSession session) {
        List<Donate> donates = donateService.findAllDonatebyUser(id);
        model.addAttribute("donate",donates );
        return "/user/history_donation";
    }


    @GetMapping("/admin")
    public String showDonateOnAdmin(Model model) {
        model.addAttribute("donate",donateService.getAllDonates());
        return "admin/HistoryDonation";
    }

//    @PostMapping("/admin")
//    public String Search(Model model, @RequestParam("username") String keyword) {
//        List<User> listuser = userService.search(keyword);
//        model.addAttribute("donate", listuser);
//        model.addAttribute("username", keyword);
//
//        return "admin/HistoryDonation";
//    }


}
