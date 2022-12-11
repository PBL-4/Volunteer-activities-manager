package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Rating_Event;
import com.example.demo1_pbl4.repository.RatingEventRepository;
import com.example.demo1_pbl4.service.RatingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingEventServiceImpl implements RatingEventService {
    @Autowired
    private RatingEventRepository ratingEventRepository;

    @Override
    public List<Rating_Event> getAllRatings() {
        return ratingEventRepository.findAll();
    }

    @Override
    public Rating_Event getRatingById(Long ratingId) {
        return ratingEventRepository.findById(ratingId).get();
    }

    @Override
    public Rating_Event insertRating(Rating_Event ratingEvent) {
        return ratingEventRepository.save(ratingEvent);
    }

    @Override
    public void updateRating(Rating_Event ratingEvent) {
        ratingEventRepository.save(ratingEvent);
    }

    @Override
    public boolean deleteRating(Long ratingId) {
        ratingEventRepository.deleteById(ratingId);
        return true;
    }

    @Override
    public List<Rating_Event> findRatingByUserId(Long UserId)
    {
        return ratingEventRepository.findRatingByUserId(UserId);
    }

}
