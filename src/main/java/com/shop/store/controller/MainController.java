package com.shop.store.controller;

import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import com.shop.store.repository.UserRepository;
import com.shop.store.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final UserService userService;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        return userService.update(model);
    }

    @RequestMapping(path = "/main/add", method = RequestMethod.GET)
    public String add() {
        return "index";
    }

    @RequestMapping(path = "/main/add", method = RequestMethod.POST)
    public String addNewUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Map<String, Object> model) {
        User user = new User(username, email, password, Role.USER, LocalDateTime.now());
        userService.addUser(user);
        return userService.update(model);

    }

    @RequestMapping(path = "/main/edit/{id}", method = RequestMethod.POST)
    public String editUsers(
            @PathVariable long id,
            @RequestParam String username,
            @RequestParam Role roles,
            Map<String, Object> model) {
        User existed = userService.findById(id);
        Assert.notNull(existed, "user not found");
        existed.setRoles(roles);
        existed.setUsername(username);
        existed.setDate(LocalDateTime.now());
        userRepository.save(existed);
        return userService.update(model);
    }

    @RequestMapping(path = "/main/remove/{id}", method = RequestMethod.POST)
    public String deleteUser(@PathVariable long id, Map<String, Object> model) {
        userService.remove(id);
        return userService.update(model);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout() {
        return "logout";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "logout";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String addUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Map<String, Object> model) {
        User user = new User(username, email, password, Role.USER, LocalDateTime.now());
        User existed = userService.findByEmail(email);
        log.info("User existed {} in the database", existed);
//        userRepository.save(user);
        return userService.update(model);
    }

//TODO find filter by roles
}
