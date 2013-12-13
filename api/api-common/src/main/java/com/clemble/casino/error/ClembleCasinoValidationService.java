package com.clemble.casino.error;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

public class ClembleCasinoValidationService {

    final private ValidatorFactory validatorFactory;

    public ClembleCasinoValidationService(ValidatorFactory validatorFactory) {
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
        ClembleCasinoException exception = ClembleCasinoException.fromConstraintViolations(violations);
        // Step 3. Generating Clemble error
        if (exception != null)
            throw exception;
    }

}
