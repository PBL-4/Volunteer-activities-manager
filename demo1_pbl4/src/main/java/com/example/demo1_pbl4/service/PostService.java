package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.User;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long postId);
    Post insertPost(Post post);
    void updatePost(Post post);
    boolean deletePost(Long postId);
}
