package com.example.demo1_pbl4.service;


import com.example.demo1_pbl4.model.UserEvent;

import java.util.List;

public interface UserEventService {
    List<UserEvent> getAllUserEvents();

    UserEvent insertUser(UserEvent userEvent);

    List<UserEvent> findMemberInEvent(Long eventId, String role);

    List<UserEvent> findAllMemberInEvent(Long eventId);

    List<UserEvent> findAllWaitingApproval(Long eventId);

    UserEvent getUserEventById(Long userId, Long eventId);

}
