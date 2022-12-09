package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.repository.RatingRepository;
import com.example.demo1_pbl4.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return ratingRepository.findById(ratingId).get();
    }

    @Override
    public Rating insertRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public boolean deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
        return true;
    }

    @Override
    public List<Rating> findRatingByUserId(Long UserId)
    {
        return ratingRepository.findRatingByUserId(UserId);
    }
}
