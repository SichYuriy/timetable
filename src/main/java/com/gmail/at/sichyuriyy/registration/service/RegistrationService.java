package com.gmail.at.sichyuriyy.registration.service;

import com.gmail.at.sichyuriyy.user.domain.User;

public interface RegistrationService {

    void register(User user) throws UserAlreadyExistException;
}
