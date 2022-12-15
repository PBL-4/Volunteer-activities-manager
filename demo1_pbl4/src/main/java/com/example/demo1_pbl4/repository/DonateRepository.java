package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Comment;
import com.example.demo1_pbl4.model.Donate;
import com.example.demo1_pbl4.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateRepository extends JpaRepository<Donate, Long> {
    @Query(value = "SELECT * from donation e where user_id=?1 ", nativeQuery = true)
    public List<Donate> findAllDonatebyUser(Long user_id);


}
