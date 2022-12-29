package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingEventRepository extends JpaRepository<Rating, Long> {
    @Query(value = "SELECT * FROM Rating WHERE user_id =?1", nativeQuery = true)
    public List<Rating> findRatingByUserId(Long UserID);


    @Query(value = "SELECT * FROM Rating " +
            "WHERE user_id=?1 and event_id=?2", nativeQuery = true)
    Rating findRatingByUserAndEvent(Long userId, Long eventId);


    //SELECT 1 trong USER_EVENT
    @Query(value = "SELECT user_event.user_user_id,role_of_event FROM user_event " +
            "WHERE user_event.event_event_id=?1 and role_of_event=?2 and is_approval=1 ", nativeQuery = true)
    List<UserEvent> findMemberInEvent(Long eventId, String role);



    @Query(value = "SELECT user_id,first_name,last_name FROM users " +
            "WHERE user_id=?1", nativeQuery = true)
    User findUserInEvent(Long userId);

    @Query(value = "SELECT point4,point5,point6 FROM rating " +
            "WHERE user_id=?1 and event_id=?2", nativeQuery = true)
    Rating findRatingInEvent(Long userId, Long eventId);

    // sửa lại phần column của rating
    @Query(value = "SELECT * FROM Rating where event_id =?1 and user_id=?2", nativeQuery = true)
    Rating findRatingByUserEventId(Long eventId, Long userId);

    //SELECT 1 trong USER_EVENT
    @Query(value = "SELECT * FROM rating WHERE rating.event_id=?1 ", nativeQuery = true)
    List<Rating> findRatingInEvent(Long eventId);


}

//   @Query(value = "SELECT user_event.user_user_id,first_name,last_name,role_of_event,point4,point5,point6 FROM user_event " +
//           "LEFT JOIN users on users.user_id=user_event.user_user_id " +
//           "LEFT JOIN rating on rating.user_id=user_event.user_user_id and user_event.event_event_id=rating.event_id " +
//           "WHERE user_event.event_event_id=?1 and role_of_event=?2 and is_approval=1 ", nativeQuery = true)
//    @Query(value="SELECT r.point4,r.point5,r.point6,userevent.eventRole,userevent.user.userId,u.firstName,u.lastName from UserEvent userevent join userevent.user u join userevent.rating r where userevent.event.eventId=?1 and userevent.eventRole=?2")
//JQPT
