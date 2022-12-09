package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long>
{
    @Query(value = "SELECT * FROM Rating u WHERE user_id = ?1",nativeQuery = true)
    public List<Rating> findRatingByUserId(Long UserID);
}
