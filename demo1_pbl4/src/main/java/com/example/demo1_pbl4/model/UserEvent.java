package com.example.demo1_pbl4.model;

import com.example.demo1_pbl4.model.dto.MemberInRating;
import lombok.Data;

import javax.persistence.*;

//@NamedNativeQuery(name = "UserPlay.findPlayerNameDtoById_Named",
//        query = "SELECT r.point4,r.point5,r.point6,userevent.eventRole,userevent.user.userId,u.firstName,u.lastName from UserEvent userevent left join userevent.user u left join userevent.rating r where userevent.event.eventId=?1 and userevent.eventRole=?2",
//        resultSetMapping = "Mapping.MemberInRating")
//@SqlResultSetMapping(name = "Mapping.MemberInRating",
//        classes = @ConstructorResult(targetClass = MemberInRating.class,
//                columns = {@ColumnResult(name = "point4"),
//                        @ColumnResult(name = "point5"),
//                        @ColumnResult(name = "point6"),
//                        @ColumnResult(name = "eventRole"),
//                        @ColumnResult(name = "userId"),
//                        @ColumnResult(name = "firstName"),
//                        @ColumnResult(name = "lastName")
//                }))
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

//    @Column(name = "point1_of_member")
//    private int memPoint1;
//    @Column(name = "point2_of_member")
//    private int memPoint2;
//    @Column(name = "point3_of_member")
//    private int memPoint3;
//
//    @Column(name = "point1_of_event")
//    private int eventPoint1;
//    @Column(name = "point2_of_event")
//    private int eventPoint2;
//    @Column(name = "point3_of_event")
//    private int eventPoint3;

    @OneToOne(mappedBy = "userEvent")
    private Rating rating;

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
}
