package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Rating_Event;

import java.util.List;

public interface RatingEventService {
    List<Rating_Event> getAllRatings();
    Rating_Event getRatingById(Long ratingId);
    Rating_Event insertRating(Rating_Event ratingEvent);
    void updateRating(Rating_Event ratingEvent);
    boolean deleteRating(Long ratingId);

    List<Rating_Event> findRatingByUserId(Long UserId);
}
