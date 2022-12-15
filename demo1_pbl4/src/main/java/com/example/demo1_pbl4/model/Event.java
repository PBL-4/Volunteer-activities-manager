package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(name = "event_name")
    private String eventName;
    private String location;

    private int age;
    @Column(name = "num_of_member")
    private int numOfMem;
    private Date beginTime;
    private Date endTime;

    private String hostname;
    private float rating;

    private double donation;

    @ManyToOne()
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
//    @ManyToMany(mappedBy = "eventList", cascade = { CascadeType.ALL },fetch=FetchType.LAZY)
//    private Set<User> users= new HashSet<>();

    //Post
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    public Event() {
    }

    public Event(String eventName, String location, int age, int numOfMem, Date beginTime, Date endTime, String hostname, double donation, Post post) {
        this.eventName = eventName;
        this.location = location;
        this.age = age;
        this.numOfMem = numOfMem;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.hostname = hostname;
        this.donation = donation;
        this.post = post;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumOfMem() {
        return numOfMem;
    }

    public void setNumOfMem(int numOfMem) {
        this.numOfMem = numOfMem;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public double getDonation() {
        return donation;
    }

    public void setDonation(double donation) {
        this.donation = donation;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
