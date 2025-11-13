package com.bezy.inventorysystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<Lowercase, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintAnnotation) {
        if(value == null) return true;
        return value.equals(value.toLowerCase());
    }
}
