package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.dto.MemberInRating;

import java.util.List;
import java.util.Optional;

public interface RatingEventService {
    List<Rating> getAllRatings();

    Rating getRatingById(Long ratingId);

    Rating insertRating(Rating ratingEvent);

    void updateRating(Rating ratingEvent);

    boolean deleteRating(Long ratingId);

    List<Rating> findRatingByUserId(Long UserId);

    Rating findRatingByUserAndEvent(Long userId, Long eventId);

    //BachLT
    List<MemberInRating> findMemberInEvent(Long eventId, String role);


    MemberInRating findMemberInEventById(Long userId, Long eventId, String role);


    Rating findRatingByUserEventId(Long eventId, Long userId);

    double calAvgPointOfEvent(Long eventId);

    void setStarEachEvent(Long eventId);

    void setStarAllEvent();


}
