package com.example.demo1_pbl4.model.dto;

import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.Role;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

public class MemberInRating {

    private int eventPoint1;
    private int eventPoint2;
    private int eventPoint3;


    private int memPoint1;

    private int memPoint2;

    private int memPoint3;

    private int avgMemPoints;

    private Long userId;

    private String firstName;

    private String lastName;
    private String eventRole;
    private Boolean isApproval;

    public MemberInRating() {
    }

    public MemberInRating(int eventPoint1, int eventPoint2, int eventPoint3, Long userId, String firstName, String lastName, String eventRole, Boolean isApproval) {
        this.eventPoint1 = eventPoint1;
        this.eventPoint2 = eventPoint2;
        this.eventPoint3 = eventPoint3;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventRole = eventRole;
        this.isApproval = isApproval;
    }

    public MemberInRating(int eventPoint1, int eventPoint2, int eventPoint3, int memPoint1, int memPoint2, int memPoint3, Long userId, String firstName, String lastName, String eventRole, Boolean isApproval) {
        this.eventPoint1 = eventPoint1;
        this.eventPoint2 = eventPoint2;
        this.eventPoint3 = eventPoint3;
        this.memPoint1 = memPoint1;
        this.memPoint2 = memPoint2;
        this.memPoint3 = memPoint3;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventRole = eventRole;
        this.isApproval = isApproval;
    }

    public int getEventPoint1() {
        return eventPoint1;
    }

    public void setEventPoint1(int eventPoint1) {
        this.eventPoint1 = eventPoint1;
    }

    public int getEventPoint2() {
        return eventPoint2;
    }

    public void setEventPoint2(int eventPoint2) {
        this.eventPoint2 = eventPoint2;
    }

    public int getEventPoint3() {
        return eventPoint3;
    }

    public void setEventPoint3(int eventPoint3) {
        this.eventPoint3 = eventPoint3;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public int getMemPoint1() {
        return memPoint1;
    }

    public void setMemPoint1(int memPoint1) {
        this.memPoint1 = memPoint1;
    }

    public int getMemPoint2() {
        return memPoint2;
    }

    public void setMemPoint2(int memPoint2) {
        this.memPoint2 = memPoint2;
    }

    public int getMemPoint3() {
        return memPoint3;
    }

    public void setMemPoint3(int memPoint3) {
        this.memPoint3 = memPoint3;
    }
}
