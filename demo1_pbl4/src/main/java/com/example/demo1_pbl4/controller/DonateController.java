package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.service.DonateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@RequestMapping("/donate")
public class DonateController {
    @Autowired
    private DonateService donateService;




}
