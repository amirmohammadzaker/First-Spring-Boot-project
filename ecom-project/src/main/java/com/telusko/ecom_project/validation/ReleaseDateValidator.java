package com.telusko.ecom_project.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ReleaseDateValidator implements ConstraintValidator<ValidReleaseDate, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDate releaseDate;

        if (value instanceof Date) {
            releaseDate = ((Date) value).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        } else if (value instanceof LocalDate) {
            releaseDate = (LocalDate) value;
        } else {
            return false;
        }

        return !releaseDate.isAfter(LocalDate.now());
    }
}