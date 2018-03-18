package com.gmail.at.sichyuriyy.user.validation;

import com.gmail.at.sichyuriyy.user.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
                "user.username.required", "username can't be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
                "user.password.required", "password can't be empty");
    }
}
