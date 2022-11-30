package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Event;
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
}
