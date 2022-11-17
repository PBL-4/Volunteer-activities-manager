package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
