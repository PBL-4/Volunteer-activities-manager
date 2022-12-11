package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.UserRepository;
import com.example.demo1_pbl4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public boolean checkExistedAccount(User user) {
        boolean check = false;
        if (userRepository.findUserByUserName(user.getUsername()) == null && userRepository.findUserByEmail(user.getEmail()) == null)
            check = true;
        return check;
    }


    public List<User> search(String keyword) {
        if (keyword != "") {
            return userRepository.search(keyword);
        }
        return userRepository.findAll();
    }
    public boolean checkLogin(String username, String password) {
        for (User user : getAllUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);

    }

    @Override
    public List<User> findUserWithSorting(String field) {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<User> findUsersWithPagination(int offset, int pageSize) {
        return userRepository.findAll(PageRequest.of(offset, pageSize));
    }
}
