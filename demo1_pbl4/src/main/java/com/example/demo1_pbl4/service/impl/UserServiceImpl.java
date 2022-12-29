package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Role;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.UserDTO;
import com.example.demo1_pbl4.repository.UserRepository;
import com.example.demo1_pbl4.service.UserService;
import com.example.demo1_pbl4.utils.UserAlreadyExistException;
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

    public User registerNewUser(User user) throws UserAlreadyExistException {
        if (checkExistedEmail(user)) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + user.getEmail());

        } else if (checkExistedUsername(user)) {
            throw new UserAlreadyExistException("There is an account with that username: "
                    + user.getUsername());
        } else {
            return userRepository.save(user);
        }
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public boolean deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return false;
    }

    public boolean checkExistedEmail(User user) {

        return userRepository.findUserByEmail(user.getEmail()) != null;
    }

    public boolean checkExistedUsername(User user) {
        return userRepository.findUserByUserName(user.getUsername()) != null;
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
    //BachLT: Chuc nang tim kiem tat ca thanh vien trong su kien cos eventId

    @Override
    public Integer countAllUser() {
        int count = 0;
        for (User u : getAllUsers()) {
            count++;
        }
        return count;
    }

    @Override
    public User getUserFromUserDTO(UserDTO userDTO) {
        User myUser = new User();
        myUser.setUsername(userDTO.getUsername());
        myUser.setPassword(userDTO.getPassword());
        myUser.setFirstName(userDTO.getFirstName());
        myUser.setLastName(userDTO.getLastName());
        myUser.setEmail(userDTO.getEmail());
        myUser.setPhoneNum(userDTO.getPhoneNum());
        myUser.setGender(userDTO.getGender());
        myUser.setAddress(userDTO.getAddress());
        if (myUser.getGender().equals("Nam")) myUser.setAvatar("/images/profile/male.jpg");
        else {
            myUser.setAvatar("/images/profile/female.png");
        }
        Role role = new Role();// Mặc định role USER
        myUser.setRole(role);
        // myUser.
        return myUser;
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findUserByEmail(email) != null;
    }
}
