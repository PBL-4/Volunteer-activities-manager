package com.example.demo1_pbl4.model;

import javax.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long idRate;

    private int point1;// diem member danh gia event cau hoi 1
    private int point2;//...                         cau hoi 2
    private int point3;//...                         cau hoi 3
    private int point4;// điểm mà host đánh giá userId này trong cau hoi 1
    private int point5;// điểm mà ... trong câu hỏi 2
    private int point6;// điểm mà ... trong câu hỏi 3

    private double star;// Diem trung binh cua event

    private String description;
    //    @OneToOne
//    private User user;
//    @OneToOne
//    private Event event;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "user_id",referencedColumnName = "user_user_id"),
            @JoinColumn(name = "event_id",referencedColumnName = "event_event_id") // 2columns
    }
    )

    private UserEvent userEvent;

    public Rating() {
        this.point4 = 0;
        this.point5 = 0;
        this.point6 = 0;
    }

    public Rating(int point4, int point5, int point6, String description) {
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.description = description;
    }

    public Rating(Long idRate, int point1, int point2, int point3, int point4, int point5, int point6, UserEvent userEvent) {
        this.idRate = idRate;
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.userEvent = userEvent;
    }

    public Rating(Long idRate, int point4, int point5, int point6, UserEvent userEvent) {
        this.idRate = idRate;
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
        this.userEvent = userEvent;
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

    public Long getIdRate() {
        return idRate;
    }

    public void setIdRate(Long idRate) {
        this.idRate = idRate;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEvent getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(UserEvent userEvent) {
        this.userEvent = userEvent;
    }

    public void setMemberPoint(int point4, int point5, int point6) {
        this.point4 = point4;
        this.point5 = point5;
        this.point6 = point6;
    }
}

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//
//    public Event getEvent()
//    {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }

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

//    public Rating(Long idRate, User user, int point1, int point2, int point3, int point4, int point5, int point6, Event event) {
//        this.idRate = idRate;
//        this.user = user;
//        this.point1 = point1;
//        this.point2 = point2;
//        this.point3 = point3;
//        this.point4 = point4;
//        this.point5 = point5;
//        this.point6 = point6;
//        this.event = event;
//    }
//
//    public Rating(User user, int point1, int point2, int point3, int point4, int point5, int point6, Event event) {
//        this.user = user;
//        this.point1 = point1;
//        this.point2 = point2;
//        this.point3 = point3;
//        this.point4 = point4;
//        this.point5 = point5;
//        this.point6 = point6;
//        this.event = event;
//    }



