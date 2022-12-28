package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.dto.Report;
import com.example.demo1_pbl4.repository.EventRepository;
import com.example.demo1_pbl4.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("ReportServiceImpl")
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EventRepository eventRepository;


    @Override
    public List<Report> reportReportCurrentYear() {
        List<Report> reports = new ArrayList<>();
        for (int m = 1; m <= 12; m++) {
            int y = Calendar.getInstance().get(Calendar.YEAR);
            Report report = new Report();
            report.setNumberEvents(eventRepository.numberEventsInMonth(m, y));
            report.setNumberVolunteers(eventRepository.countVolunteerInMonthAndYear(m, y));
            report.setMonth(m);
            reports.add(report);

        }
        return reports;

    }

    @Override
    public List<Report> reportReportByYear(int year) {
        List<Report> reports = new ArrayList<>();
        for (int m = 1; m <= 12; m++) {
            Report report = new Report();
            report.setNumberEvents(eventRepository.numberEventsInMonth(m, year));
            report.setNumberVolunteers(eventRepository.countVolunteerInMonthAndYear(m, year));
            report.setMonth(m);
            reports.add(report);
        }
        return reports;
    }

    @Override
    public List<Report> donationEventCurrentYear() {
        int y = Calendar.getInstance().get(Calendar.YEAR);
        List<Event> reports = eventRepository.donationEventinYear(y);
        List<Report> listReport = new ArrayList<>();
        for (Event e : reports) {
            Report report = new Report();
            report.setDonationEvents(e.getFund());
            report.setNameEvents(e.getEventName());
            listReport.add(report);

        }
        return listReport;
    }

    @Override
    public List<Report> donationEventByYear(int year) {
        List<Event> reports = eventRepository.donationEventinYear(year);
        List<Report> listReport = new ArrayList<>();
        for (Event e : reports) {
            Report report = new Report();
            report.setDonationEvents(e.getFund());
            report.setNameEvents(e.getEventName());
            listReport.add(report);
        }
        return listReport;
    }

    @Override
    public List<Double> getDonatePerMByY(int year) {
        List<Double> donateASC = new ArrayList<>();
        for (Report r : donationEventByYear(year)) {
            donateASC.add(r.getDonationEvents());
        }
        return donateASC;
    }

    @Override
    public List<Integer> getEventPerMByY(int year) {
        List<Integer> numOfEventsMonthly = new ArrayList<>();
        for (Report r : reportReportByYear(year)) {
            numOfEventsMonthly.add(r.getNumberEvents());
        }
        return numOfEventsMonthly;
    }

    @Override
    public List<Integer> getUserPerMByY(int year) {
        List<Integer> numOfUsersMonthly = new ArrayList<>();
        for (Report r : reportReportByYear(year)) {
            numOfUsersMonthly.add(r.getNumberVolunteers());
        }
        return numOfUsersMonthly;
    }
}
