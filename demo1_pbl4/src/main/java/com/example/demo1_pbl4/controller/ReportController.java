package com.example.demo1_pbl4.controller;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.dto.Report;
import com.example.demo1_pbl4.service.EventService;
import com.example.demo1_pbl4.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private EventService eventService;

    @GetMapping("/thongke")
    public String thongke(Model model, @RequestParam(value = "year",required = false)Integer myYear) {
        Integer[] numEvents = new Integer[100];
        Integer[] years = new Integer[100];
        List<String> nameEvents = new ArrayList<>();
        String[] nameevents = {"chuong trinh thien nguyen1","chuong trinh thien nguyen thang 7 : noi chao yeu thuong","chuong trinh thien nguyen3","chuong trinh thien nguyen4","chuong trinh thien nguyen5","chuong trinh thien nguyen6"};
        Double[] numDonate = new Double[100];
        String[] months = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
        int count = 0;
        int num =0;

        List<Report> reports = new ArrayList<>();
        List<Report> listReport = reportService.DonationEventinYear();
        int y = Calendar.getInstance().get(Calendar.YEAR);
        if (myYear!=null) {
            reports = reportService.reportReportByYear(myYear);
            model.addAttribute("cY", myYear);
        } else {
            reports = reportService.reportReportCurrentYear();
            model.addAttribute("cY", y);
        }

        for (Report r : reports) {
            numEvents[count] = r.getNumberEvents();
            count++;
        }

        for (Report l : listReport) {
           numDonate[num] = l.getDonationEvents();
           nameEvents.add(l.getNameEvents());
           num++;
        }

        for (int i = 2010; i <= y; i++) {
            years[i - 2009] = i;
        }
        model.addAttribute("years", years);
        model.addAttribute("months", months);
        model.addAttribute("numEvents", numEvents);
        model.addAttribute("numDonates",numDonate);
        model.addAttribute("nameEvents",nameEvents.toArray());

        //  model.addAttribute("report", reports);
        return "admin/Report";
    }

}
