package com.example.demo1_pbl4.repository;


import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.UserEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, UserEventId> {
    @Query(value = "SELECT * FROM user_event WHERE event_event_id =?1 and user_user_id =?2", nativeQuery = true)
    public UserEvent findRatingByUserAndEventId(Long eventId, Long userId);
}
