package com.example.demo1_pbl4.model;

import javax.persistence.*;


@Entity
@Table(name="rating_member")
public class Rating_Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ratings")
    private Long idRate;
    @OneToOne
    private User user;
    private int point1;
    private int point2;
    private int point3;

    @OneToOne
    private Event event;

    public Rating_Member()
    {

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

    public Rating_Member(Long idRate, User user, int point1, int point2, int point3, Event event) {
        this.idRate = idRate;
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.event = event;
    }

    public Rating_Member(User user, int point1, int point2, int point3, Event event) {
        this.user = user;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.event = event;
    }
}
