package com.clemble.casino.error;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class ClembleCasinoException extends RuntimeException {

    /**
     * Generated value
     */
    private static final long serialVersionUID = -8129180501783483734L;

    final private ClembleCasinoFailureDescription failure;

    private ClembleCasinoException(ClembleCasinoFailureDescription failure) {
        super(failure.toString());
        this.failure = failure;
    }

    public ClembleCasinoFailureDescription getFailureDescription() {
        return failure;
    }

    public static ClembleCasinoException fromError(ClembleCasinoError error) {
        return new ClembleCasinoException(ClembleCasinoFailureDescription.withErrors(Collections.singleton(error)));
    }

    public static ClembleCasinoException fromCodes(Collection<String> errors) {
        return new ClembleCasinoException(ClembleCasinoFailureDescription.withErrorCodes(errors));
    }

    public static <T> ClembleCasinoException fromConstraintViolations(Set<ConstraintViolation<T>> violations){
        // Step 1. Accumulating error messages
        Set<String> errorCodes = new HashSet<String>();
        for (ConstraintViolation<T> error : violations) {
            errorCodes.add(error.getMessage());
        }
        // Step 2. Generating Clemble error
        return ClembleCasinoException.fromCodes(errorCodes);
    }

    // TODO get rid of this 
    public static ClembleCasinoException fromGenericConstraintViolations(Set<ConstraintViolation<?>> violations){
        // Step 1. Accumulating error messages
        Set<String> errorCodes = new HashSet<String>();
        for (ConstraintViolation<?> error : violations) {
            errorCodes.add(error.getMessage());
        }
        // Step 2. Generating Clemble error
        return ClembleCasinoException.fromCodes(errorCodes);
    }

    public static ClembleCasinoException fromDescription(ClembleCasinoFailureDescription description) {
        return new ClembleCasinoException(description);
    }

}
