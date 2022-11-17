package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
}
