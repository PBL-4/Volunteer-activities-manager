package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Event;
import com.example.demo1_pbl4.model.Status;
import com.example.demo1_pbl4.repository.EventRepository;
import com.example.demo1_pbl4.repository.StatusRepository;
import com.example.demo1_pbl4.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        Page<Event> eventPages= eventRepository.findEventOfHost(userId, role, pageable);
   //     List<Event> events=new ArrayList<>();
        for(Event e: eventPages.getContent())
        {
            e.setCurrentMem(eventRepository.countCurrentMember(e.getEventId()));
            e.setWaitingApproval(eventRepository.countWaitingApproval(e.getEventId()));
      //      System.out.println("idEvent: "+e.getEventId());
          System.out.println("curent mem: "+eventRepository.countCurrentMember(e.getEventId()));
        //    events.add(e);
            e=eventRepository.save(e);
            System.out.println("curent mem after save: "+(e.getCurrentMem()));
        }

        return eventRepository.findEventOfHost(userId, role, pageable);
    }

    public void setStatusByDateTime(List<Event> eventList) {
        try {
            for (Event event : eventList) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
                Date now = new Date();//
                Date beginDate = sdf.parse(event.getBeginTime().toString());
                Date finishDate = sdf.parse(event.getEndTime().toString());
                System.out.println("Date hien tai: " + sdf.format(now));
                now=sdf.parse(now.toString()); // không cùng định dạng sẽ ko ss
                if (beginDate.after(now)) {
                    Status status=statusRepository.findById(1L).get();
                    event.setStatus(status);
                    System.out.println("Here 1");
                } else if (beginDate.before(now) && finishDate.after(now)) {
                    Status status=statusRepository.findById(2L).get();
                    event.setStatus(status);
                    System.out.println("Here 2");
                } else if (finishDate.before(now)) { // Thời gian kết thúc muộn hơn thời gian thực
                    Status status=statusRepository.findById(3L).get();
                    event.setStatus(status);
                    System.out.println("Here 3");
                } else {
                    System.out.println("Loi thoi gian");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
//    @Override
//    public void createEventByHost(Long userId, Long eventId, String role, Boolean isApproval) {
//        eventRepository.createEventByHost(userId, eventId, role, isApproval);
//    }
}
