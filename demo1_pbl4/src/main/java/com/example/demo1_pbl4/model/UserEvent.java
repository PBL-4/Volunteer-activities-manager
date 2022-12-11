package com.example.demo1_pbl4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_event")
@Data
public class UserEvent {
    @EmbeddedId
    private UserEventId userEventId;

    @ManyToOne
    @MapsId("eventId")
    private Event event;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Column(name = "role_of_event")
    private String eventRole;

    @Column(name = "is_approval")
    private Boolean isApproval;

    @Column(name = "point1_of_member")
    private int memPoint1;
    @Column(name = "point2_of_member")
    private int memPoint2;
    @Column(name = "point3_of_member")
    private int memPoint3;

    @Column(name = "point1_of_event")
    private int eventPoint1;
    @Column(name = "point2_of_event")
    private int eventPoint2;
    @Column(name = "point3_of_event")
    private int eventPoint3;

    public UserEvent() {
    }

    public UserEvent(UserEventId userEventId, Event event, User user, String eventRole, Boolean isApproval) {
        this.userEventId = userEventId;
        this.event = event;
        this.user = user;
        this.eventRole = eventRole;
        this.isApproval = isApproval;
    }

    public UserEvent(Event event, User user, String eventRole, Boolean isApproval) {
        this.event = event;
        this.user = user;
        this.eventRole = eventRole;
        this.isApproval = isApproval;
        this.userEventId=new UserEventId(user.getUserId(),event.getEventId());
    }


    public UserEventId getUserEventId() {
        return userEventId;
    }

    public void setUserEventId(UserEventId userEventId) {
        this.userEventId = userEventId;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
