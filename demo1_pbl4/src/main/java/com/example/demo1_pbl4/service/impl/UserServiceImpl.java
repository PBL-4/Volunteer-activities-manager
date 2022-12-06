package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.UserRepository;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User insertUser(User user)
    {
        User temp = null;
        if(checkExistedAccount(user) == true) {
            return userRepository.save(user);
        } else return temp;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return false;
    }
    public User findUserByUserName(String username)
    {
        return userRepository.findUserByUserName(username);
    }
    public User findUserByEmail(String email)
    {
        return userRepository.findUserByUserName(email);
    }
    public boolean checkExistedAccount(User user)
    {
        boolean check = false;
        if(userRepository.findUserByUserName(user.getUsername()) == null && userRepository.findUserByEmail(user.getEmail()) == null) check = true;
        return check;
    }
}
