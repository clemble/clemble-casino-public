package com.clemble.casino.error;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class ClembleException extends RuntimeException {

    /**
     * Generated value
     */
    private static final long serialVersionUID = -8129180501783483734L;

    final private ClembleError failure;

    private ClembleException(ClembleError failure) {
        super(failure.toString());
        this.failure = failure;
    }

    public ClembleError getFailureDescription() {
        return failure;
    }

    public static ClembleException withServerError(ClembleErrorCode error) {
        return new ClembleException(ClembleError.withErrors(Collections.singleton(error)));
    }

    public static ClembleException withFieldError(String field, ClembleErrorCode error) {
        return new ClembleException(ClembleError.withFieldError(field, error));
    }

    public static ClembleException withCodes(Collection<String> errors) {
        return new ClembleException(ClembleError.withErrorCodes(errors));
    }

    public static <T> ClembleException withViolations(Set<ConstraintViolation<T>> violations){
        // Step 1. Accumulating error messages
        Set<String> errorCodes = new HashSet<String>();
        for (ConstraintViolation<T> error : violations) {
            errorCodes.add(error.getMessage());
        }
        // Step 2. Generating Clemble error
        return ClembleException.withCodes(errorCodes);
    }

    // TODO get rid of this 
    public static ClembleException fromGenericConstraintViolations(Set<ConstraintViolation<?>> violations){
        // Step 1. Accumulating error messages
        Set<String> errorCodes = new HashSet<String>();
        for (ConstraintViolation<?> error : violations) {
            errorCodes.add(error.getMessage());
        }
        // Step 2. Generating Clemble error
        return ClembleException.withCodes(errorCodes);
    }

    public static ClembleException fromDescription(ClembleError description) {
        return new ClembleException(description);
    }

}
