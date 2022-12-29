package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

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

    @Column(name = "current_member")
    private int currentMem;

    @Column(name = "waiting_approval")
    private int waitingApproval;


    private Date beginTime;
    private Date endTime;

    private String hostname;

    private double rating;

    private int star;

    private double fund;

    @Column(name = "is_approval")
    private boolean isApproval;

    @ManyToOne()
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<UserEvent> userEvent;

    //Post
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Donate> donation;

    public Event() {
        this.currentMem = 0;
        this.waitingApproval = 0;
    }

    public Event(String eventName, String location, int age, int numOfMem, Date beginTime, Date endTime, String hostname, Post post) {
        this.eventName = eventName;
        this.location = location;
        this.age = age;
        this.numOfMem = numOfMem;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.hostname = hostname;
        this.post = post;
    }

    public Event(String eventName, String location, int age, int numOfMem, Date beginTime, Date endTime, String hostname, double fund, Post post) {
        this.eventName = eventName;
        this.location = location;
        this.age = age;
        this.numOfMem = numOfMem;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.hostname = hostname;
        this.fund = fund;
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

    public double getFund() {
        return fund;
    }

    public void setFund(double donation) {
        this.fund = donation;
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

    public int getCurrentMem() {
        return currentMem;
    }

    public void setCurrentMem(int currentMem) {
        this.currentMem = currentMem;
    }

    public int getWaitingApproval() {
        return waitingApproval;
    }

    public void setWaitingApproval(int waitingApproval) {
        this.waitingApproval = waitingApproval;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Set<UserEvent> getUserEvent() {
        return userEvent;
    }

    public void setUserEvent(Set<UserEvent> userEvent) {
        this.userEvent = userEvent;
    }

    public Set<Donate> getDonation() {
        return donation;
    }

    public void setDonation(Set<Donate> donation) {
        this.donation = donation;
    }

    public boolean isApproval() {
        return isApproval;
    }

    public void setApproval(boolean approval) {
        isApproval = approval;
    }
}
