package com.gmail.at.sichyuriyy.user.service;

import com.gmail.at.sichyuriyy.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

}

