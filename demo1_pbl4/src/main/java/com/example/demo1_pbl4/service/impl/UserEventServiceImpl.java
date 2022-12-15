package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.repository.UserEventRepository;
import com.example.demo1_pbl4.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public UserEvent insertUser(UserEvent userEvent) {
        return userEventRepository.save(userEvent);

    }

    @Override
    public UserEvent findUserEventByUserAndEventId(Long eventId, Long UserId)
    {
        return userEventRepository.findRatingByUserAndEventId(eventId, UserId);
    }
}
