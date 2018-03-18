package com.gmail.at.sichyuriyy.registration.service;

import com.gmail.at.sichyuriyy.user.domain.User;
import com.gmail.at.sichyuriyy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserService userService;

    @Autowired
    public RegistrationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void register(User user) throws UserAlreadyExistException {
        userService.findByUserName(user.getUsername())
                .ifPresent(this::throwUserAlreadyExist);
        userService.save(user);
    }

    private void throwUserAlreadyExist(User user) {
        throw new UserAlreadyExistException(String.format("User %s is already exist", user.getUsername()));
    }
}
