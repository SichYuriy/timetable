package com.gmail.at.sichyuriyy.security.service;

import com.gmail.at.sichyuriyy.user.domain.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface SecurityService {

    Optional<User> findLoggedInUser();

    void login(String username, String password) throws UsernameNotFoundException;

    void logout(HttpServletRequest request, HttpServletResponse response);
}
