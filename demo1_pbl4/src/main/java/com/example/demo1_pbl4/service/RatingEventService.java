package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.dto.MemberInRating;

import java.util.List;

public interface RatingEventService {
    List<Rating> getAllRatings();
    Rating getRatingById(Long ratingId);
    Rating insertRating(Rating ratingEvent);
    void updateRating(Rating ratingEvent);
    boolean deleteRating(Long ratingId);

    List<Rating> findRatingByUserId(Long UserId);

    //BachLT
    List<MemberInRating> findMemberInEvent(Long eventId, String role);
}
