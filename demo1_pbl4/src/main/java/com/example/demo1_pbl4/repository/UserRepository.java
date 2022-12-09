package com.example.demo1_pbl4.repository;

import com.example.demo1_pbl4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username=?1", nativeQuery = true)
    User findUserByUsername(String username);
}
