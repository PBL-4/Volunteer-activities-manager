package com.example.demo1_pbl4.model;

import com.example.demo1_pbl4.model.dto.MemberInRating;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "user_event")
@AssociationOverrides(value = {
        @AssociationOverride(name = "pk.user_user_id", joinColumns = @JoinColumn(referencedColumnName = "user_user_id")),
        @AssociationOverride(name = "pk.event_event_id", joinColumns = @JoinColumn(referencedColumnName = "event_event_id"))
})
@Data
public class UserEvent {
    @EmbeddedId
    private UserEventId userEventId;

    @ManyToOne()
    @MapsId("eventId")
    private Event event;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @Column(name = "role_of_event")
    private String eventRole;

    @Column(name = "is_approval")
    private Boolean isApproval;

    @Column(name = "info_of_mem")
    private String infoOfMem;

    @Column(name = "skill")
    private String skill;

    @OneToOne(mappedBy = "userEvent")
    private Rating rating;

    public UserEvent() {
    }

    public UserEvent(User user, String eventRole) {
        this.user = user;
        this.eventRole = eventRole;
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
        this.userEventId = new UserEventId(user.getUserId(), event.getEventId());
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

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getInfoOfMem() {
        return infoOfMem;
    }

    public void setInfoOfMem(String infoOfMem) {
        this.infoOfMem = infoOfMem;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
