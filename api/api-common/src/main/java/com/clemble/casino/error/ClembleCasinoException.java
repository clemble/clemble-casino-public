package com.clemble.casino.error;

import com.clemble.casino.player.PlayerAware;

import java.util.Collection;
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
        return new ClembleCasinoException(new ClembleCasinoFailureDescription().addError(error));
    }

    public static ClembleCasinoException fromError(ClembleCasinoError error, String player) {
        return new ClembleCasinoException(new ClembleCasinoFailureDescription().addProblem(new ClembleCasinoFailure(error, player)));
    }

    public static ClembleCasinoException fromError(ClembleCasinoError error, String player, String key) {
        return new ClembleCasinoException(new ClembleCasinoFailureDescription().addProblem(new ClembleCasinoFailure(player, key, error)));
    }

    public static ClembleCasinoException withKey(ClembleCasinoError error, String key) {
        return new ClembleCasinoException(new ClembleCasinoFailureDescription().addProblem(new ClembleCasinoFailure(PlayerAware.DEFAULT_PLAYER, key, error)));
    }


    public static ClembleCasinoException fromFailures(Collection<ClembleCasinoFailure> failures) {
        return new ClembleCasinoException(new ClembleCasinoFailureDescription().setProblems(failures));
    }

    public static ClembleCasinoException fromCodes(Collection<String> errors) {
        return new ClembleCasinoException(new ClembleCasinoFailureDescription().setErrors(ClembleCasinoError.forCodes(errors)));
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
