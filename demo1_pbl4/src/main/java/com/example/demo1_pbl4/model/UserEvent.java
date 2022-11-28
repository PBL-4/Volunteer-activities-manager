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

    public UserEvent() {
    }

    public UserEvent(UserEventId userEventId, Event event, User user, String eventRole, Boolean isApproval) {
        this.userEventId = userEventId;
        this.event = event;
        this.user = user;
        this.eventRole = eventRole;
        this.isApproval = isApproval;
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
}
