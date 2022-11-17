package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
}
