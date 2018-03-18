package com.gmail.at.sichyuriyy.validation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorDto {
    private String fieldName;
    private String errorCode;
    private String defaultMessage;
}
