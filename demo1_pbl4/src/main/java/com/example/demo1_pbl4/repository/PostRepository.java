package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
