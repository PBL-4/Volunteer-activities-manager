package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Rating_Member;
import com.example.demo1_pbl4.repository.RatingMemberRepository;
import com.example.demo1_pbl4.service.RatingMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingMemberServiceImpl implements RatingMemberService {
    @Autowired
    private RatingMemberRepository RatingMemberRepository;

    @Override
    public List<Rating_Member> getAllRatings() {
        return RatingMemberRepository.findAll();
    }

    @Override
    public Rating_Member getRatingById(Long ratingId) {
        return RatingMemberRepository.findById(ratingId).get();
    }

    @Override
    public Rating_Member insertRating(Rating_Member RatingMember) {
        return RatingMemberRepository.save(RatingMember);
    }

    @Override
    public void updateRating(Rating_Member RatingMember) {
        RatingMemberRepository.save(RatingMember);
    }

    @Override
    public boolean deleteRating(Long ratingId) {
        RatingMemberRepository.deleteById(ratingId);
        return true;
    }

    @Override
    public List<Rating_Member> findRatingByUserId(Long UserId)
    {
        return RatingMemberRepository.findRatingByUserId(UserId);
    }

}
