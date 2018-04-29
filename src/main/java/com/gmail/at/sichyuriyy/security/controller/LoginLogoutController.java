package com.gmail.at.sichyuriyy.security.controller;

import com.gmail.at.sichyuriyy.security.service.SecurityService;
import com.gmail.at.sichyuriyy.user.dto.UserDto;
import com.gmail.at.sichyuriyy.user.dto.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/session")
public class LoginLogoutController {

    private final SecurityService securityService;
    private final UserTransformer userTransformer;

    @Autowired
    public LoginLogoutController(SecurityService securityService,
                                 UserTransformer userTransformer) {
        this.securityService = securityService;
        this.userTransformer = userTransformer;
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody UserDto loginUserDto, HttpServletRequest request) {
        securityService.login(request, loginUserDto.getUsername(), loginUserDto.getPassword());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        securityService.logout(request, response);
    }

    @GetMapping("/currentUser")
    public UserDto getCurrentUser() {
        return userTransformer
                .toDto(securityService.findLoggedInUser().orElse(null));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void userNotFound() {
    }

}