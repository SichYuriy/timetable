package com.gmail.at.sichyuriyy.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ValidationErrorDto {
    private List<FieldErrorDto> errors;

    public ValidationErrorDto(FieldErrorDto ...errors) {
        this.errors = Arrays.asList(errors);
    }
}
