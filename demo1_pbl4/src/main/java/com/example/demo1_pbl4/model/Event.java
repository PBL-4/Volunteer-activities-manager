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

    private String location;

    private String age;
    @Column(name="num_of_member")
    private int numOfMem;
    private Date beginTime;
    private Date endTime;

    private float rating;

    public Event() {
    }



    @ManyToMany(mappedBy = "eventList", cascade = { CascadeType.ALL },fetch=FetchType.LAZY)
    private Set<User> users= new HashSet<>();


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
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
}
