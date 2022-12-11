package com.example.demo1_pbl4.service;


import com.example.demo1_pbl4.model.UserEvent;

import java.util.List;

public interface UserEventService {
    List<UserEvent> getAllUserEvents();

    UserEvent  insertUser(UserEvent userEvent);

}
