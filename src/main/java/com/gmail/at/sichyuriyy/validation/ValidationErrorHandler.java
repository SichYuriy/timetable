package com.gmail.at.sichyuriyy.validation;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class ValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDto validationError(MethodArgumentNotValidException ex) {
        return ValidationErrorDto.builder()
                .errors(ex.getBindingResult().getFieldErrors().stream()
                        .map(this::fieldErrorDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private FieldErrorDto fieldErrorDto(FieldError fieldError) {
        return FieldErrorDto.builder()
                .fieldName(fieldError.getField())
                .defaultMessage(fieldError.getDefaultMessage())
                .errorCode(fieldError.getCode())
                .build();
    }
}
