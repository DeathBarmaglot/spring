package com.shop.web.controller;

import com.shop.dao.UserDao;
import com.shop.web.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private UserDao userDao;

    //TODO userDao

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(Map<String, Object> model) {
            return "logout";
    }

    @RequestMapping(path = "/main", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, Map<String, Object> model) {
        userDao.deleteById(id);
        return "redirect:/main";
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        model.put("users", userDao.findAll());
        return "main";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String addUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Map<String, Object> model) {
        User user = new User(username, email, password);
        userDao.save(user);
        Iterable<User> users = userDao.findAll();
        model.put("users", users);
        return "main";
    }

}
