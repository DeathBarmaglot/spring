package com.shop.store.service;

import com.shop.store.entity.Role;
import com.shop.store.entity.User;
import com.shop.store.entity.ConfirmationToken;
import com.shop.store.registration.EmailValidator;
import com.shop.store.registration.RegistrationRequest;
import com.shop.store.registration.token.ConfirmationTokenService;
import com.shop.store.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor

public class RegistrationService {
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return userService.sinUp(new User( request.getUsername(), request.getEmail(), request.getPassword(), Role.USER, LocalDateTime.now()));
    }
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
//        userService.enabledUser(confirmationToken.getUser().getEmail());
        return "confirmed";
    }
}

