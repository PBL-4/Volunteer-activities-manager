package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
