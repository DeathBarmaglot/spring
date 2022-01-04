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
public class UserController {
    @Autowired
    private UserDao userDao;

//    @RequestMapping(path = "/", method = RequestMethod.GET)
//    public String auth(Map<String, Object> model) {
//        return "redirect:/login";
//    }

//    @RequestMapping(path = "/login", method = RequestMethod.GET)
//    public String login(Map<String, Object> model) {
//        return "login";
//    }

    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, Map<String, Object> model) {
        userDao.deleteById(id);
        return "redirect:/user";
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        model.put("users", userDao.findAll());
        return "users";
    }

    @RequestMapping(path = "/users/add", method = RequestMethod.GET)
    public String getAdd(Map<String, Object> model) {
        model.put("users", userDao.findAll());
        return "new";
    }

    @RequestMapping(path = "/users/add", method = RequestMethod.POST)
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            Map<String, Object> model) {
        User user = new User(username, password);
        userDao.save(user);
        Iterable<User> users = userDao.findAll();
        model.put("users", users);
        return "redirect:users";
    }

}
