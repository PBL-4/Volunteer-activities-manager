package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Rating_Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingEventRepository extends JpaRepository<Rating_Event,Long>
{
    @Query(value = "SELECT * FROM Rating WHERE user_user_id =?1",nativeQuery = true)
    public List<Rating_Event> findRatingByUserId(Long UserID);
}
