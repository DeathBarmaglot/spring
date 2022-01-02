package com.shop.web.controller;

import com.shop.web.entity.User;
import com.shop.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String login(Map<String, Object> model) {
        return "login";
    }


    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String users(Map<String, Object> model) {
        model.put("users", userDao.findAll());
        return "users";
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String addUsers(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password, Map<String, Object> model) {
        User user = new User(username, password, email);
        userDao.save(user);
        model.put("users", user);
        return "users";
    }

    @RequestMapping(path = "/filter", method = RequestMethod.POST)
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<User> users;

        if (filter != null && filter.isEmpty()) {
            users = userDao.findByEmail(filter);
        }else {
            users = userDao.findAll();
        }

        model.put("users", users);
        return "users";
    }

}