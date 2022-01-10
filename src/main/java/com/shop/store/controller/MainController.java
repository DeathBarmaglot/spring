package com.shop.store.controller;

import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import com.shop.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public String main(Map<String, Object> model) {
        return update(model);
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
        userRepository.save(user);
        return update(model);
    }

    @RequestMapping(path = "/main/edit/{id}", method = RequestMethod.POST)
    public String editUsers(
            @PathVariable long id,
            @RequestParam String username,
            @RequestParam Role roles,
            Map<String, Object> model) {
        User existed = userRepository.findById(id).get();
        Assert.notNull(existed, "user not found");
        existed.setRoles(roles);
        existed.setUsername(username);
        existed.setDate(LocalDateTime.now());
        userRepository.save(existed);
        return update(model);
    }

    @RequestMapping(path = "/main/remove/{id}", method = RequestMethod.POST)
    public String deleteUser(@RequestParam Long id, Map<String, Object> model) {
        userRepository.deleteById(id);
        return update(model);
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout() {
        return "logout";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String addUser(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Map<String, Object> model) {
        User user = new User(username, email, password, Role.USER, LocalDateTime.now());
        Optional<User> existed = userRepository.findByEmail(email);
        log.info("User existed {} in the database", existed);
//        userRepository.save(user);
        return update(model);
    }

    private String update(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }
//TODO find filter by roles
}
