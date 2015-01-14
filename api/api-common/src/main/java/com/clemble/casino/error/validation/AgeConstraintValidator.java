package com.clemble.casino.error.validation;

import org.joda.time.DateTime;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeConstraintValidator implements ConstraintValidator<AgeConstraint, DateTime>{

    @Override
    public void initialize(AgeConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(DateTime value, ConstraintValidatorContext context) {
        return value == null || value.getMillis() < System.currentTimeMillis() ? true : false;
    }

}
