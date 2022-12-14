package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.UserEventId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository  extends JpaRepository<UserEvent, UserEventId> {
}
