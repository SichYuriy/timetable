package com.gmail.at.sichyuriyy.registration.controller;

import com.gmail.at.sichyuriyy.registration.service.RegistrationService;
import com.gmail.at.sichyuriyy.registration.service.UserAlreadyExistException;
import com.gmail.at.sichyuriyy.user.dto.UserDto;
import com.gmail.at.sichyuriyy.user.dto.UserTransformer;
import com.gmail.at.sichyuriyy.user.validation.UserValidator;
import com.gmail.at.sichyuriyy.validation.FieldErrorDto;
import com.gmail.at.sichyuriyy.validation.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserTransformer userTransformer;

    public RegistrationController(RegistrationService registrationService, UserTransformer userTransformer) {
        this.registrationService = registrationService;
        this.userTransformer = userTransformer;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(new UserValidator());
    }

    @PostMapping
    public void registration(@Valid @RequestBody UserDto userDto) {
        registrationService.register(userTransformer.fromDto(userDto));
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDto userAlreadyExist(UserAlreadyExistException ex) {
        return new ValidationErrorDto(FieldErrorDto.builder()
                .fieldName("username")
                .errorCode("user.username.alreadyExist")
                .defaultMessage(ex.getMessage())
                .build());
    }
}
