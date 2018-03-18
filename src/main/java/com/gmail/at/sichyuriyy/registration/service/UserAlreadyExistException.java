package com.gmail.at.sichyuriyy.registration.service;

class UserAlreadyExistException extends IllegalArgumentException {

    UserAlreadyExistException(String message) {
        super(message);
    }
}
