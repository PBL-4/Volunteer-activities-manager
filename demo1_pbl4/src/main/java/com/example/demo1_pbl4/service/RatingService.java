package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> getAllRatings();
    Rating getRatingById(Long ratingId);
    Rating insertRating(Rating rating);
    void updateRating(Rating rating);
    boolean deleteRating(Long ratingId);

    List<Rating> findRatingByUserId(Long UserId);
}
