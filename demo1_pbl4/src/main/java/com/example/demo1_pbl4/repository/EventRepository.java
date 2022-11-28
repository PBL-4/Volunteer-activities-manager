package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    @Query(value="SELECT * from Event e where location=?1 and event_name LIKE %?2%",nativeQuery = true)
    public List<Event> findEventByLocationAndKeyword(String location, String keyword);
}
