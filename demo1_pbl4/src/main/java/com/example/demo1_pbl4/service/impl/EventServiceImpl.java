package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Status;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.UserEvent;
import com.example.demo1_pbl4.repository.EventRepository;
import com.example.demo1_pbl4.repository.StatusRepository;
import com.example.demo1_pbl4.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private StatusRepository statusRepository;


    @Override
    public List<Event> getAllEvents() {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findAll();
    }

    @Override
    public Event getEventById(Long eventId) {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findById(eventId).get();
    }

    @Override
    public Event insertEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public boolean deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
        return true;
    }

    @Override
    public List<Event> findEventByLocationAndKeyword(String location, String keyword) {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findEventByLocationAndKeyword(location, keyword);
    }

    @Override
    public List<Event> findEventByLocation(String location) {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findEventByLocation(location);
    }
// Find event :
    @Override
    public List<Event> findEventByEventName(String eventName) {
        setStatusByDateTime(eventRepository.findAll());
        if (eventName != "") {
            return eventRepository.findEventByEventName(eventName);
        }
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findEventByHostname(String hostname) {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findEventByHostname(hostname);
    }

    @Override
    public List<Event> findEventWithSorting(String field) {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Event> findEventWithPagination(int offset, int pageSize) {
        setStatusByDateTime(eventRepository.findAll());
        return eventRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Event> findEventOfHost(Long userId, String role, Pageable pageable) {
        setStatusByDateTime(eventRepository.findAll());
        Page<Event> eventPages = eventRepository.findEventOfHost(userId, role, pageable);
        //     List<Event> events=new ArrayList<>();
        for (Event e : eventPages.getContent()) {
            e.setCurrentMem(eventRepository.countCurrentMember(e.getEventId()));
            e.setWaitingApproval(eventRepository.countWaitingApproval(e.getEventId()));
            //      System.out.println("idEvent: "+e.getEventId());
            System.out.println("current mem: " + eventRepository.countCurrentMember(e.getEventId()));
            //    events.add(e);
            e = eventRepository.save(e);
            System.out.println("current mem after save: " + (e.getCurrentMem()));
        }

        return eventRepository.findEventOfHost(userId, role, pageable);
    }

    @Override
    public Page<Event> findEventByLocation(String location, Pageable pageable) {
        return eventRepository.findEventByLocation(location, pageable);
    }

    @Override
    public Page<Event> findEventByHostname(String hostname, Pageable pageable) {
        return eventRepository.findEventByHostname(hostname, pageable);
    }

    @Override
    public Page<Event> findEventByEventName(String eventName, Pageable pageable) {
        return eventRepository.findEventByEventName(eventName, pageable);
    }

    // ---- End find event
    public void setStatusByDateTime(List<Event> eventList) {
        try {
            for (Event event : eventList) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                Date now = new Date();//
                String beginDateStr = sdf.format(event.getBeginTime());
                String finishDateStr = sdf.format(event.getEndTime());
                Date beginDate = sdf.parse(beginDateStr);
                Date finishDate = sdf.parse(finishDateStr);
                now = sdf.parse(sdf.format(now));   // không cùng định dạng sẽ ko ss
                if (beginDate.after(now)) {
                    Status status = statusRepository.findById(1L).get();
                    event.setStatus(status);
                } else if (beginDate.before(now) && finishDate.after(now)) {
                    Status status = statusRepository.findById(2L).get();
                    event.setStatus(status);
                } else if (finishDate.before(now)) { // Thời gian kết thúc muộn hơn thời gian thực
                    Status status = statusRepository.findById(3L).get();
                    event.setStatus(status);
                } else {
                    System.out.println("Loi thoi gian");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Integer countAllEvents() {
        int count = 0;
        for (Event e : eventRepository.findAll()) {
            count++;
        }
        return count;
    }

    @Override
    public List<Event> findAllFinishEvent() {
        List<Event> events = new ArrayList<>();
        Date now = new Date();
        for (Event e : eventRepository.findAll()) {
            if (e.getBeginTime().before(now)) {
                events.add(e);
            }
        }
        return events;
    }

    @Override
    public Boolean isFinishEvent(Long eventId) {
        Event e = eventRepository.findById(eventId).get();
        Date now = new Date();
        if (e.getEndTime().before(now))
            return true;
        return false;
    }

    @Override
    public List<Event> sortEventByRating() {
        List<Event> eventList = eventRepository.findAll();
        Collections.sort(eventList, (o1, o2) -> {
            return Double.compare(o2.getRating(), o1.getRating());
        });// Thay cho new Comparator
        return eventList;
    }


}
