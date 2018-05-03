package com.gmail.at.sichyuriyy.timetable.validation;

import com.gmail.at.sichyuriyy.timetable.dto.CreateTimetableDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CreateTimetableValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateTimetableDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
                "timetable.title.required", "title can't be empty");
    }
}
