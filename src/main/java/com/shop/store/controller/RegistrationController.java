package com.shop.store.controller;

import com.shop.store.entity.User;
import com.shop.store.registration.RegistrationRequest;
import com.shop.store.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/registration")
@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String signUp(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path="confirm")
    public String confirm(@RequestParam("token") String token){
        return  registrationService.confirmToken(token);
    }

    //TODO registration token 15 min
}
