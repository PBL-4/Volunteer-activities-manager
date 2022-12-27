package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.UserEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();

    Event getEventById(Long eventId);

    Event insertEvent(Event event);

    void updateEvent(Event event);

    boolean deleteEvent(Long eventId);

    // Tìm kiếm lấy dach
    List<Event> findEventByLocationAndKeyword(String location, String keyword);

    List<Event> findEventByLocation(String location);

    List<Event> findEventByEventName(String eventName);

    List<Event> findEventByHostname(String hostname);

    // Tối ưu: Tìm kiếm phân trang
    Page<Event> findEventByLocation(String location, Pageable pageable);

    Page<Event> findEventByHostname(String hostname, Pageable pageable);

    Page<Event> findEventByEventName(String eventName, Pageable pageable);

    //-- Kết thúc tìm kiếm

    List<Event> findEventWithSorting(String field);

    Page<Event> findEventWithPagination(int offset, int pageSize);

    Page<Event> findEventOfHost(Long userId, String role, Pageable pageable);

    //void createEventByHost(Long userId,Long eventId, String role,Boolean isApproval);
    //
    Integer countAllEvents();

    List<Event> findAllFinishEvent();

    //check if event is finish
    Boolean isFinishEvent(Long eventId);

    List<Event> sortEventByRating();

}
