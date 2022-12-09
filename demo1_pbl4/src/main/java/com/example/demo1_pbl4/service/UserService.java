package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User insertUser(User user);

    void updateUser(User user);

    boolean deleteUser(Long userId);

    boolean checkLogin(String username, String password);

    User findUserByUsername(String usernane);
}
