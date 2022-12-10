package com.example.demo1_pbl4.service.impl;

import com.example.demo1_pbl4.model.Role;
import com.example.demo1_pbl4.repository.RoleRepository;
import com.example.demo1_pbl4.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }
}
