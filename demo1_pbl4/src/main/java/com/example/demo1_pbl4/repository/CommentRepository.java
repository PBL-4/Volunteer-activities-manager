package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "SELECT COUNT(*) FROM comments",nativeQuery = true)
    Long countComments();

    @Query(value = "SELECT COUNT(*) FROM comments where post_id=?1",nativeQuery = true)
    Long countCommentsByPost(Long postId);

    @Query(value = "SELECT * FROM comments where post_id=?1",nativeQuery = true)
    List<Comment> findAllByPost(Long postId);
}
