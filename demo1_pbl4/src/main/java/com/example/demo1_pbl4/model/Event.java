package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="events")
public class Event {

    @Id
    @Column(name="event_id")
    private Long eventId;

    @Column(name="event_name")
    private String eventName;
    private String location;

    private String age;
    @Column(name="num_of_member")
    private int numOfMem;
    private Date beginTime;
    private Date endTime;

    private String hostname;
    private float rating;

    private double donation;

    @ManyToOne()
    @JoinColumn(name="status_id",nullable=false)
    private Status status;
    @ManyToMany(mappedBy = "eventList", cascade = { CascadeType.ALL },fetch=FetchType.LAZY)
    private Set<User> users= new HashSet<>();

    //Post
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="post_id")
    private Post post;

    public Event() {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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
