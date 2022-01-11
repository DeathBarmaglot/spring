package com.shop.store.service;

import com.shop.store.entity.ConfirmationToken;
import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import com.shop.store.registration.token.ConfirmationTokenService;
import com.shop.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ConfirmationTokenService confirmationTokenService;


    public String sinUp(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new IllegalStateException("This email address is already being used");
        }
//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getRoles().equals(Role.USER)) {
                System.out.println(user.getUsername());
            }
            ;
        }
        return users;
    }

    public void remove(long id) {
        if(userRepository.existsById(id)){
        userRepository.deleteById(id);}
        else{throw new IllegalStateException("user not exist");}
    }

    public String update(Map<String, Object> model) {
        Iterable<User> users = userRepository.findAll();
        model.put("users", users);
        return "main";
    }

    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public void addUser(User user) {
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw new IllegalStateException("email exists");
        }
        userRepository.save(user);
    }
}
