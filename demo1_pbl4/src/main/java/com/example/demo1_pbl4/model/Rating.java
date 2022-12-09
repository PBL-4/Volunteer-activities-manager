package com.example.demo1_pbl4.model;

import javax.persistence.*;

@Entity
@Table(name="rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ratings")
    private Long idRate;
    @OneToOne
    private User user;
    private double point1;// Diem Cau hoi 1 - doi voi event
    private double point2;// Diem Cau hoi 2 - doi voi event
    private double point3;// Diem cau hoi 3 - doi voi event
    private double point4;// Diem cau hoi 4 - doi voi host / user
    private double point5;// Diem cau hoi 5 - doi voi host / user
    private double point6;// Diem cau hoi 6 - doi voi host / user

    private String eventRole;
    @OneToOne
    private Event event;

    public Rating() {
    }

    public double getPoint1() {
        return point1;
    }

    public void setPoint1(double point1) {
        this.point1 = point1;
    }

    public double getPoint2() {
        return point2;
    }

    public void setPoint2(double point2) {
        this.point2 = point2;
    }

    public double getPoint3() {
        return point3;
    }

    public void setPoint3(double point3) {
        this.point3 = point3;
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


    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getPoint4() {
        return point4;
    }

    public void setPoint4(double point4) {
        this.point4 = point4;
    }

    public double getPoint5() {
        return point5;
    }

    public void setPoint5(double point5) {
        this.point5 = point5;
    }

    public double getPoint6() {
        return point6;
    }

    public void setPoint6(double point6) {
        this.point6 = point6;
    }

    public String getEventRole() {
        return eventRole;
    }

    public void setEventRole(String eventRole) {
        this.eventRole = eventRole;
    }

    public Rating(Long idRate, User user, double point1, double point2, double point3, double point4, double point5, double point6, String eventRole, Event event) {
        this.idRate = idRate;
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.eventRole = eventRole;
        this.event = event;
    }

    public Rating(User user, double point1, double point2, double point3, double point4, double point5, double point6, String eventRole, Event event) {
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.eventRole = eventRole;
        this.event = event;
    }
}
