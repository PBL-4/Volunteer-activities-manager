package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Comment;


import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();
    Comment getCommentById(Long commentId);
    Comment insertComment(Comment comment);
    void updateComment(Comment comment);
    boolean deleteComment(Long commentId);
    Long countComment();
}
