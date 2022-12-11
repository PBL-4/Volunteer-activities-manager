package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Rating_Member;

import java.util.List;

public interface RatingMemberService {
    List<Rating_Member> getAllRatings();
    Rating_Member getRatingById(Long ratingId);
    Rating_Member insertRating(Rating_Member ratingEvent);
    void updateRating(Rating_Member ratingEvent);
    boolean deleteRating(Long ratingId);

    List<Rating_Member> findRatingByUserId(Long UserId);
}
