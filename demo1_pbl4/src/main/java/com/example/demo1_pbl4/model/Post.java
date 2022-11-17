package com.example.demo1_pbl4.model;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_post")
    private Long idPost;
    private String title;
    private String content;
    private Long idUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToOne(fetch = FetchType.LAZY)
    private Event event;


    public Post() {
    }

    public Post(Long idPost, String title, String content, Long idUser) {
        this.idPost = idPost;
        this.title = title;
        this.content = content;
        this.idUser = idUser;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
