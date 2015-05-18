package com.clemble.casino.error;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

public class ClembleValidationService {

    final private ValidatorFactory validatorFactory;

    public ClembleValidationService(ValidatorFactory validatorFactory) {
        if (validatorFactory == null)
            throw new NullPointerException();
        this.validatorFactory = validatorFactory;
    }

    public <T> void validate(T object) {
        // Step 1. Validating provided input
        Set<ConstraintViolation<T>> violations = validatorFactory.getValidator().validate(object);
        if (violations.isEmpty())
            return;
        // Step 2. Accumulating error codes
        ClembleException exception = ClembleException.fromConstraintViolations(violations);
        // Step 3. Generating Clemble error
        if (exception != null)
            throw exception;
    }

}
