package com.example.demo1_pbl4.service;

import com.example.demo1_pbl4.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
}
