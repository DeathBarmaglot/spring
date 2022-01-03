package com.shop.service;

import com.shop.web.entity.Role;
import com.shop.web.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String name);

    User getUser(String username);

    List<User> getUsers();
}
