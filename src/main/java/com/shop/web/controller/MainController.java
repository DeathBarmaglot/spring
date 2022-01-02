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

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        model.put("users", userDao.findAll());
        return "main";
    }

    @RequestMapping(path = "/main", method = RequestMethod.POST)
    public String postMain(
            @RequestParam String email,
            @RequestParam String password, Map<String, Object> model) {
        User user = new User(email, password);
        model.put("users", user);
        userDao.save(new User(password, email));
        return "main";
    }
}
