package com.example.demo1_pbl4.repository;


import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.UserEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, UserEventId> {
    @Query(value = "SELECT * FROM user_event WHERE event_event_id =?1 and user_user_id =?2", nativeQuery = true)
    public Optional<UserEvent> findRatingByUserAndEventId(Long eventId, Long userId);

    @Query(value="SELECT * from user_event where user_user_id=?1 and role_of_event=?2",nativeQuery=true)
    public List<UserEvent> findAllEventWithMember(Long userId,String role);
}
