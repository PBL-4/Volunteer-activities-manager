package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.dto.Report;

import java.util.List;

public interface ReportService {
    public List<Report> reportReportCurrentYear();
    List<Report> reportReportByYear(int year);

    public List<Report> donationEventCurrentYear();
    List<Report> donationEventByYear(int year);

    //Lấy tất cả tổng donate từng tháng theo năm
    List<Double> getDonatePerMByY(int year);

    List<Integer> getEventPerMByY(int year);

    List<Integer> getUserPerMByY(int year);
}
