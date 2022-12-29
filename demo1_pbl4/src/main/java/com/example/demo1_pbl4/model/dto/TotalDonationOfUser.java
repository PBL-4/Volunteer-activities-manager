package com.example.demo1_pbl4.model.dto;

public class TotalDonationOfUser{

    private Long userId;

    private String firsName;

    private  String lastName;

    private double Total;

    private String address;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firsName;
    }

    public void setFirstName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public TotalDonationOfUser(Long user_id, String firsName, String lastName, double total){
        this.userId = user_id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.Total = total;
    }

}
