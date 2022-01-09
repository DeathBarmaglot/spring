package com.shop.store.controller;

import com.shop.store.registration.RegistrationRequest;
import com.shop.store.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@RequestMapping(path = "/login")
@RestController
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token){
        return  registrationService.confirmToken(token);
    }
}
