package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query(value="SELECT * from Events e where location=?1 and event_name LIKE %?2%",nativeQuery = true)
    public List<Event> findEventByLocationAndKeyword(String location, String keyword);

    @Query(value="SELECT * from Events e where location LIKE %?1%",nativeQuery = true)
    public List<Event> findEventByLocation(String location);
    @Query(value="SELECT * from Events e where hostname LIKE %?1%",nativeQuery = true)
    public List<Event> findEventByHostname(String hostname);
    @Query(value="SELECT * from Events  where event_name LIKE %?1%",nativeQuery = true)
    public List<Event> findEventByEventName(String eventName);

    // Lấy sự kiện dựa vào host của event: BachLT
    @Query(value="SELECT * FROM Events LEFT JOIN user_event on events.event_id=user_event.event_event_id" +
            " WHERE user_user_id=?1  and role_of_event=?2 and is_approval= 1",nativeQuery=true)
    public Page<Event> findHostOfEvent(Long userId, String role, Pageable pageable);



}
