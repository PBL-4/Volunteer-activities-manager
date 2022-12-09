package com.example.demo1_pbl4.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
     private static final long serialVersionUID = 20L;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Khong co cai nay, se gap phai loi 001
    private Long userId;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;
    @Column(name = "phone_number", nullable = true)
    private String phoneNum;
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name="gender",nullable=true)
    private String gender;

    @Nullable
    private String avatar;

    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Post> posts;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Post> comments;

    public User() {

    }

    public User(Long userId, String firstName, String lastName, String phoneNum, String email, String username, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String phoneNum, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(@Nullable String avatar) {
        this.avatar = avatar;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}






//    public Set<Event> getEvents() {
//        return eventList;
//    }
//
//    public void setEvents(Set<Event> events) {
//        this.eventList = events;
//    }


//    @ManyToMany(cascade={
//            CascadeType.ALL
//    },fetch = FetchType.EAGER)
//    @JoinTable(
//            name="users_events",
//            joinColumns={
//                   @JoinColumn(name = "user_id"),
//            },
//            inverseJoinColumns={
//                    @JoinColumn(name="event_id"),
//                   // @JoinColumn(name="is_appoval")
//            }
//
//    )
//
//   private Set<Event> eventList=new HashSet<Event>();
