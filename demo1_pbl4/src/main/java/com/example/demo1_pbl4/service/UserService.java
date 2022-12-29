package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.model.dto.MemberInRating;
import com.example.demo1_pbl4.model.dto.UserDTO;
import com.example.demo1_pbl4.utils.UserAlreadyExistException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User registerNewUser(User user) throws UserAlreadyExistException;

    User updateUser(User user);

    boolean deleteUser(Long userId);

    public List<User> search(String keyword);

    boolean checkLogin(String username, String password);

    User findUserByUsername(String username);


    List<User> findUserWithSorting(String field);

    Page<User> findUsersWithPagination(int offset, int pageSize);

    //  List<MemberInRating> findMemberInEvent(Long eventId, String role);
    Integer countAllUser();

    User getUserFromUserDTO(UserDTO userDTO);

    boolean emailExists(String email);



}
