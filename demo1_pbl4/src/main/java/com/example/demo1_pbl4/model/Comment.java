package com.example.demo1_pbl4.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_comment")
    private Long idComment;
    @ManyToOne(optional = true)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(optional = true)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="comment_content")
    private String commentContent;

    @Column(name="comment_date")
    private Date commentDate;


    public Comment() {
    }


    public Comment(Long idComment, Post post, User user, String commentContent, Date commentDate) {
        this.idComment = idComment;
        this.post = post;
        this.user = user;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
