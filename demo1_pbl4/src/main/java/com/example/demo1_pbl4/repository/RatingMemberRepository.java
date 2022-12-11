package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Rating_Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingMemberRepository extends JpaRepository<Rating_Member,Long>
{
    @Query(value = "SELECT * FROM Rating u WHERE user_id = ?1",nativeQuery = true)
    public List<Rating_Member> findRatingByUserId(Long UserID);
}
