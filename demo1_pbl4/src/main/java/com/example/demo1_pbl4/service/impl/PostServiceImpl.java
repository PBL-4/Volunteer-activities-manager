package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Post;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.PostRepository;
import com.example.demo1_pbl4.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public Post insertPost(Post post) {

        return postRepository.save(post);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public boolean deletePost(Long postId) {
        postRepository.deleteById(postId);
        return true;
    }
}
