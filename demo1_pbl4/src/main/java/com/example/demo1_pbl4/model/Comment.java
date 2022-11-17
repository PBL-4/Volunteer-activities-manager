package com.example.demo1_pbl4.model;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_comment")
    private Long idComment;
    @OneToOne(optional = true)
    private Post post;

    @OneToOne(optional = true)
    private User user;

    private String comment;

    public Comment() {
    }

    public Comment(Long idComment, Post post, User user, String comment) {
        this.idComment = idComment;
        this.post = post;
        this.user = user;
        this.comment = comment;
    }

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
