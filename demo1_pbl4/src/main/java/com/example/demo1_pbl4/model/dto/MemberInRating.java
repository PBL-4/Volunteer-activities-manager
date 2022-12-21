package com.example.demo1_pbl4.model.dto;

import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.Role;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

public class MemberInRating {

    private int point1;
    private int point2;
    private int point3;
    private int point4;
    private int point5;
    private int point6;// rating

    private double avgMemPoints;

    private Long userId;
    private Long eventId;

    private String firstName;

    private String lastName;
    private String eventRole;// user_event
    private Boolean isApproval;

    public MemberInRating() {
    }

    public MemberInRating(int point4, int point5, int point6, Long userId, String firstName, String lastName, String eventRole) {
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventRole = eventRole;
    }

    public MemberInRating(int point1, int point2, int point3, int point4, int point5, int point6, Long userId, String firstName, String lastName, String eventRole) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventRole = eventRole;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEventRole() {
        return eventRole;
    }

    public void setEventRole(String eventRole) {
        this.eventRole = eventRole;
    }

    public Boolean getApproval() {
        return isApproval;
    }

    public void setApproval(Boolean approval) {
        isApproval = approval;
    }

    public int getPoint1() {
        return point1;
    }

    public void setPoint1(int point1) {
        this.point1 = point1;
    }

    public int getPoint2() {
        return point2;
    }

    public void setPoint2(int point2) {
        this.point2 = point2;
    }

    public int getPoint3() {
        return point3;
    }

    public void setPoint3(int point3) {
        this.point3 = point3;
    }

    public int getPoint4() {
        return point4;
    }

    public void setPoint4(int point4) {
        this.point4 = point4;
    }

    public int getPoint5() {
        return point5;
    }

    public void setPoint5(int point5) {
        this.point5 = point5;
    }

    public int getPoint6() {
        return point6;
    }

    public void setPoint6(int point6) {
        this.point6 = point6;
    }

    public double getAvgMemPoints() {
        return avgMemPoints;
    }

    public void setAvgMemPoints(double avgMemPoints) {
        this.avgMemPoints = avgMemPoints;
    }
}
