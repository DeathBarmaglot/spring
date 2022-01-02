package com.shop.controller;

import com.shop.domain.User;
import com.shop.repo.FoodRepo;
import com.shop.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;
    private FoodRepo foodRepo;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public String users(Map<String, Object> model) {
        model.put("users", userRepo.findAll());
        return "users";
    }

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        model.put("users", foodRepo.findAll());
        return "main";
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    protected String login() {
        return "login";
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public String add(
            @RequestParam String email,
            @RequestParam String password, Map<String, Object> model) {
        userRepo.save(new User(password, email));
        return "redirect:/users";
    }
    @RequestMapping
    public String filter(@RequestParam String email, Map<String,Object> model ){
        return "main";
    }

}