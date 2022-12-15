package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.UserEvent;
<<<<<<< HEAD
=======
import com.example.demo1_pbl4.model.dto.MemberInRating;
>>>>>>> 2233aed19dc46741ca40d0c1950e139d8088be83
import com.example.demo1_pbl4.repository.UserEventRepository;
import com.example.demo1_pbl4.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> 2233aed19dc46741ca40d0c1950e139d8088be83
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
<<<<<<< HEAD
    public UserEvent findUserEventByUserAndEventId(Long eventId, Long UserId)
    {
        return userEventRepository.findRatingByUserAndEventId(eventId, UserId);
=======
    public List<UserEvent> findMemberInEvent(Long eventId, String role) {
        List<UserEvent> userEventList = new ArrayList<>();
        for (UserEvent userEvent : userEventRepository.findAll()) {
            if (userEvent.getEvent().getEventId() == eventId && userEvent.getEventRole().equals(role)) {
                userEventList.add(userEvent);
            }
        }
        return userEventList;
>>>>>>> 2233aed19dc46741ca40d0c1950e139d8088be83
    }
}
