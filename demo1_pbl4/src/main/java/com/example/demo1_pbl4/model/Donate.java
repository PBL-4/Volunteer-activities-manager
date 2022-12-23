package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "donation")
public class Donate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_donation")
    private Long idDonation;

    @ManyToOne
    @JoinColumn(name="user_Id")
    private User user;

    @OneToOne
    @JoinColumn(name = "event_Id")
    private Event event;

    private Date donateDate;

    private double Money;
    @Column(name="donate_comment", columnDefinition = "TEXT")
    private String comment;

    public double getMoney() {
        return Money;
    }

    public void setMoney(double money) {
        Money = money;
    }

    public Donate(double money) {
        Money = money;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Donate() {
    }

    public Donate(User user) {
        this.user = user;
    }

    public Donate(Long idDonation , Event event, Date donateDate) {
        this.idDonation = idDonation;
        this.donateDate = donateDate;
        this.event = event;
    }

    public Long getIdDonation() {
        return idDonation;
    }

    public void setIdDonation(long idDonation) {
        this.idDonation = idDonation;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(Date donateDate) {
        this.donateDate = donateDate;
    }


}
