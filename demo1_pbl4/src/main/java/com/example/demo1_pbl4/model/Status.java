package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name="status_id")
    private Long id;

    private String statusName;

    @OneToMany(mappedBy="status",fetch = FetchType.LAZY)
    private Set<Event> events;

    public Status() {
    }

    public Status(Long id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public Status(Long id, String statusName, Set<Event> events) {
        this.id = id;
        this.statusName = statusName;
        this.events = events;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
