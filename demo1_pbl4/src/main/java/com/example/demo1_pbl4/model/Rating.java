package com.example.demo1_pbl4.model;

import javax.persistence.*;

@Entity
@Table(name="rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ratings")
    private Long idRate;
    @OneToOne
    private User user;
    private double star;// Diem trung binh

    @OneToOne
    private Event event;

    public Rating() {
    }

    public Rating(Long idRate, User user, double star, Event event) {
        this.idRate = idRate;
        this.user = user;
        this.star = star;
        this.event = event;
    }

    public Long getIdRate() {
        return idRate;
    }

    public void setIdRate(Long idRate) {
        this.idRate = idRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
