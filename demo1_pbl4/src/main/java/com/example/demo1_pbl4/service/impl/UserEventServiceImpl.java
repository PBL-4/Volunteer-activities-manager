package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.UserEvent;

import com.example.demo1_pbl4.model.UserEventId;

import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.repository.UserEventRepository;
import com.example.demo1_pbl4.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserEventServiceImpl implements UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Override
    public List<UserEvent> getAllUserEvents() {
        return userEventRepository.findAll();
    }

    @Override
    public UserEvent insertUserEvent(UserEvent userEvent) {
        return userEventRepository.save(userEvent);

    }

    @Override
    public UserEvent updateUserEvent(UserEvent userEvent) {
        return userEventRepository.save(userEvent);
    }

    @Override
    public UserEvent findUserEventByUserAndEventId(Long eventId, Long UserId) {
        return userEventRepository.findRatingByUserAndEventId(eventId, UserId);
    }

    public List<UserEvent> findMemberInEvent(Long eventId, String role) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId && userEvent.getEventRole().equals(role)) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
    }

    @Override
    public List<UserEvent> findAllMemberInEvent(Long eventId) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
    }

    @Override
    public List<UserEvent> findAllWaitingApproval(Long eventId) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId && !userEvent.getApproval()) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
    }

    @Override
    public UserEvent getUserEventById(Long userId, Long eventId) {
        return userEventRepository.findById(new UserEventId(userId, eventId)).get();
    }

    @Override
    public List<UserEvent> findAllEventWithMember(Long userId, String role) {
        return userEventRepository.findAllEventWithMember(userId, role);
    }

    @Override
    public boolean deleteUserEvent(Long userId, Long eventId) {
        if (getUserEventById(userId, eventId) != null) {
            userEventRepository.deleteById(new UserEventId(userId, eventId));
            return true;
        } else {
            return false;
        }
    }
}
