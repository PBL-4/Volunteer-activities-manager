package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rating")
public class Rating_Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ratings")
    private Long idRate;
    @OneToOne
    private User user;
    private int point1;
    private int point2;
    private int point3;
    private int point4;
    private int point5;
    private int point6;
    private String comment;

//    private String eventname;
//
//    private Date beginTime;
//    private Date endTime;

    @OneToOne
    private Event event;

    public Rating_Event() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdRate() {
        return idRate;
    }

    public void setIdRate(Long idRate) {
        this.idRate = idRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

//    public String getEventname() {
//        return eventname;
//    }
//
//    public void setEventname(String eventname) {
//        this.eventname = eventname;
//    }
//
//    public Date getBeginTime() {
//        return beginTime;
//    }
//
//    public void setBeginTime(Date beginTime) {
//        this.beginTime = beginTime;
//    }
//
//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    public Rating_Event(Long idRate, User user, int point1, int point2, int point3, int point4, int point5, int point6, Event event) {
        this.idRate = idRate;
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.event = event;
    }

    public Rating_Event(User user, int point1, int point2, int point3, int point4, int point5, int point6, Event event) {
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.event = event;
    }

    public Rating_Event(User user, int point1, int point2, int point3, String comment, Event event) {
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.comment = comment;
        this.event = event;
    }

    public Rating_Event(User user, int point4, int point5, int point6, Event event) {
        this.user = user;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.event = event;
    }
}
