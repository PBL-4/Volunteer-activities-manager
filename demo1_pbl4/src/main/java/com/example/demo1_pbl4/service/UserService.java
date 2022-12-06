package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.UserRepository;

import java.util.List;

public interface UserService{
    List<User> getAllUsers();
    User getUserById(Long userId);
    User insertUser(User user);
    void updateUser(User user);
    boolean deleteUser(Long userId);
    User findUserByUserName(String username);
}
