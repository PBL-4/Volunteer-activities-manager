package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postId;
    private String title;
    private String content;

    //Dư
   // private Long idUser;

    @Column(name = "post_date")
    private Date postDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToOne(mappedBy="post",fetch = FetchType.LAZY)
    private Event event;


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

}
