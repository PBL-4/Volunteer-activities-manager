package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;
    @Column(name="title",columnDefinition = "TEXT")
    private String title;
    @Column(name="content",columnDefinition = "TEXT")
    private String content;

    //Dư
   // private Long idUser;

    @Column(name = "post_date")
    private Date postDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToOne(mappedBy="post",cascade=CascadeType.ALL)
    private Event event;


    @OneToMany(mappedBy= "post",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<Comment> comment;



    public Post() {
    }

    public Post(Long postId, String title, String content, Date postDate, User user, Event event) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.event = event;
    }

    public Post(Long postId, String title, String content, Date postDate, User user, Event event, Set<Comment> comment) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.event = event;
        this.comment = comment;
    }

    public Post(String title, String content, Date postDate, User user) {
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long idPost) {
        this.postId = idPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }
}
