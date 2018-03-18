package com.gmail.at.sichyuriyy.user.service;

import com.gmail.at.sichyuriyy.user.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String username);

    void save(User user);
}
