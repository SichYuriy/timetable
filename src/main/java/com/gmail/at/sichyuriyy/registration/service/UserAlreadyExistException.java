package com.gmail.at.sichyuriyy.registration.service;

public class UserAlreadyExistException extends IllegalArgumentException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
