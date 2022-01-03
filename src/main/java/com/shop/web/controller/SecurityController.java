//package com.shop.web.controller;
//
//import com.shop.dao.UserDao;
//import com.shop.web.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class SecurityController {
//    @Autowired
//    private UserDao userDao;
//
//    @GetMapping("/registration")
//    public String registration() {
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String addUser(User user, Map<String, Object> model) {
////        List<User> userFromDb = userDao.findByUsername(user.getEmail());
//
////        if (userFromDb != null) {
//            model.put("message", "User exists!");
//            return "registration";
//        }
//
//        user.setActive(true);
////        user.setRoles(Collections.singleton(Role.USER));
//        userDao.save(user);
//
//        return "redirect:/login";
//    }
//}