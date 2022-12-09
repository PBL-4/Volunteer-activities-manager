package com.example.demo1_pbl4.security;

import com.example.demo1_pbl4.model.Role;
import com.example.demo1_pbl4.model.User;
import com.example.demo1_pbl4.repository.RoleRepository;
import com.example.demo1_pbl4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            System.out.println("Khong tim thay user");
            throw new UsernameNotFoundException("User not found");
        } else {
            List<GrantedAuthority> grantList = new ArrayList<>();
            for (Role role : roleRepository.findAll()) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                grantList.add(authority);
            }

            UserDetails userDetails  = new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantList);
       //     CustomUserDetails customUserDetails=new CustomUserDetails((User)userDetails);


            return userDetails;
        }
// return new CustomUserDetails(user);
    }
}
