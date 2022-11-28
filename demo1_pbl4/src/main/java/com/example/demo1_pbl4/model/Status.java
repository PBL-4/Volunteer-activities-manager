package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name="status_id")
    private int id;

    private String statusName;

    @OneToMany(mappedBy="status",fetch = FetchType.LAZY)
    private Set<Event> events;

    public Status() {
    }

    public Status(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
