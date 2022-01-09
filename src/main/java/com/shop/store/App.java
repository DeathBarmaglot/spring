package com.shop.store;

import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import com.shop.store.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
//            userService.save(new User("user", "qwerty@gmail.com", "1", Role.USER, LocalDateTime.now()));
//            userService.save(new User("Joe", "asdgfh@gmail.com", "2", Role.ADMIN, LocalDateTime.now()));
//            userService.save(new User("Jim", "fghlkj@gmail.com", "3", Role.USER, LocalDateTime.now()));

        };
    }
}
