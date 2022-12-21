package com.example.demo1_pbl4.model.dto;

public class Report {
    private int month;

    private int numberEvents;

    private double donationEvents;

    public String getNameEvents() {
        return nameEvents;
    }

    public void setNameEvents(String nameEvents) {
        this.nameEvents = nameEvents;
    }

    public Report(String nameEvents) {
        this.nameEvents = nameEvents;
    }

    private String nameEvents;

    public double getDonationEvents() {
        return donationEvents;
    }

    public void setDonationEvents(double donationEvents) {
        this.donationEvents = donationEvents;
    }

    public Report(double donationEvents) {
        this.donationEvents = donationEvents;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumberEvents() {
        return numberEvents;
    }

    public void setNumberEvents(int numberEvents) {
        this.numberEvents = numberEvents;
    }

    public Report(){}
    public Report(int month, int numberEvents) {
        this.month = month;
        this.numberEvents = numberEvents;
    }
}
