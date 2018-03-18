package com.gmail.at.sichyuriyy.registration.controller;

import com.gmail.at.sichyuriyy.registration.service.RegistrationService;
import com.gmail.at.sichyuriyy.user.dto.UserDto;
import com.gmail.at.sichyuriyy.user.dto.UserTransformer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserTransformer userTransformer;

    public RegistrationController(RegistrationService registrationService, UserTransformer userTransformer) {
        this.registrationService = registrationService;
        this.userTransformer = userTransformer;
    }

    @PostMapping
    public void registration(@RequestBody UserDto userDto) {
        registrationService.register(userTransformer.fromDto(userDto));
    }
}
