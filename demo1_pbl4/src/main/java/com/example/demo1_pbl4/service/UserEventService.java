package com.example.demo1_pbl4.service;


import com.example.demo1_pbl4.model.UserEvent;

import java.util.List;

public interface UserEventService {
    List<UserEvent> getAllUserEvents();


    UserEvent insertUserEvent(UserEvent userEvent);

    UserEvent updateUserEvent(UserEvent userEvent);

    UserEvent findUserEventByUserAndEventId(Long eventId, Long userId);

    List<UserEvent> findMemberInEvent(Long eventId, String role);

    List<UserEvent> findAllMemberInEvent(Long eventId);

    List<UserEvent> findAllWaitingApproval(Long eventId);

    UserEvent getUserEventById(Long userId, Long eventId);

    List<UserEvent> findAllEventWithMember(Long userId,String role);

    boolean deleteUserEvent(Long userId, Long eventId);


}
