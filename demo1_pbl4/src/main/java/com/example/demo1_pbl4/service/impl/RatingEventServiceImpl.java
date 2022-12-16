package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Rating;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.repository.RatingEventRepository;
import com.example.demo1_pbl4.service.RatingEventService;
import com.example.demo1_pbl4.service.UserEventService;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingEventServiceImpl implements RatingEventService {

    @Autowired
    private UserService userService;
    @Autowired
    private RatingEventRepository ratingEventRepository;

    @Autowired
    private UserEventService userEventService;

    @Override
    public List<Rating> getAllRatings() {
        return ratingEventRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long ratingId) {
        return ratingEventRepository.findById(ratingId).get();
    }

    @Override
    public Rating insertRating(Rating ratingEvent) {
        return ratingEventRepository.save(ratingEvent);
    }

    @Override
    public void updateRating(Rating ratingEvent) {
        ratingEventRepository.save(ratingEvent);
    }

    @Override
    public boolean deleteRating(Long ratingId) {
        ratingEventRepository.deleteById(ratingId);
        return true;
    }

    @Override
    public List<Rating> findRatingByUserId(Long UserId) {
        return ratingEventRepository.findRatingByUserId(UserId);
    }

    @Override
    public Rating findRatingByUserAndEvent(Long userId, Long eventId) {
        return ratingEventRepository.findRatingByUserAndEvent(userId, eventId);
    }


    @Override
    public List<MemberInRating> findMemberInEvent(Long eventId, String role) {
        List<MemberInRating> members = new ArrayList<>();

        for (UserEvent userEvent : userEventService.findMemberInEvent(eventId, role)) {
            MemberInRating member = new MemberInRating();
            User user = userService.getUserById(userEvent.getUser().getUserId());
            System.out.println(user.getUserId());
            Rating rating = ratingEventRepository.findRatingByUserAndEvent(user.getUserId(), userEvent.getEvent().getEventId());
            if (rating == null) {
                rating = new Rating();
                System.out.println("Rating được tạo");
            } else {
                int p4 = rating.getPoint4();
                int p5 = rating.getPoint5();
                int p6 = rating.getPoint6();
                double avgPoint = (p4 + p5 + p6) / 3.0;
                member.setPoint4(rating.getPoint4());
                member.setPoint5(rating.getPoint5());
                member.setPoint6(rating.getPoint6());
                member.setAvgMemPoints(avgPoint);
            }
            member.setUserId(userEvent.getUser().getUserId());
            member.setEventId(userEvent.getEvent().getEventId());
            member.setFirstName(user.getFirstName());
            member.setLastName(user.getLastName());
            member.setEventRole(userEvent.getEventRole());
            System.out.println("\n first name: " + member.getFirstName());
            members.add(member);
        }
        return members;
    }


    @Override
    public MemberInRating findMemberInEventById(Long userId, Long eventId, String role) {
        for (MemberInRating member : findMemberInEvent(eventId, role)) {
            if (member.getUserId() == userId) {
                return member;
            }
        }
        return null;
    }

    @Override
    public Rating findRatingByUserEventId(Long eventId, Long userId) {
        return ratingEventRepository.findRatingByUserEventId(eventId, userId);

    }

}
