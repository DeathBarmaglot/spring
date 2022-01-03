package com.shop;

import com.shop.service.UserService;
import com.shop.web.entity.Role;
import com.shop.web.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role("USER"));
            userService.saveRole(new Role("MANAGER"));
            userService.saveRole(new Role("ADMIN"));
            userService.saveRole(new Role("SUPER_ADMIN"));

            userService.saveUser(new User( "John", "12345", "qwerty@gmail.com", new ArrayList<>()));
            userService.saveUser(new User( "Joe", "54321", "asdgfh@gmail.com", new ArrayList<>()));
            userService.saveUser(new User( "Jim", "12321", "fghlkj@gmail.com", new ArrayList<>()));

            userService.addRoleToUser("John","USER");
            userService.addRoleToUser("Joe","MANAGER");
            userService.addRoleToUser("Jim","ADMIN");
            userService.addRoleToUser("Jim","SUPER_ADMIN");
        };
    }
}
