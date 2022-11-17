package com.example.demo1_pbl4.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // Khong co cai nay, se gap phai loi 001
    private Long userId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    @Column(name="phone_number")
    private String phoneNum;
    private String email;
    private String username;
    private String password;

    @Column(name="is_admin")
    private Boolean isAdmin;

   // private List<String> comments;


    @ManyToMany(cascade={
            CascadeType.ALL
    },fetch = FetchType.LAZY)
    @JoinTable(
            name="users_events",
            joinColumns={
                   @JoinColumn(name = "user_id")
            },
            inverseJoinColumns={
                        @JoinColumn(name="event_id")
            }

    )
   private Set<Event> eventList=new HashSet<Event>();
    public User() {
    }


    @OneToMany(mappedBy= "user",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<Post>posts;


    public User(Long userId, String firstName, String lastName, String phoneNum, String email, String username, String password, Set<Event> events) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.username = username;
        this.password = password;
        this.eventList = events;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Event> getEvents() {
        return eventList;
    }

    public void setEvents(Set<Event> events) {
        this.eventList = events;
    }
}
