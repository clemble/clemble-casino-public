package com.clemble.casino.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.clemble.casino.error.*;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;

import com.clemble.casino.utils.CollectionUtils;

public class ClembleCasinoExceptionMatcherFactory {

    private ClembleCasinoExceptionMatcherFactory() {
        throw new IllegalAccessError();
    }

    public static Matcher<ClembleCasinoException> fromPossibleErrors(final ClembleCasinoError... errors) {
        final Collection<ClembleCasinoError> expectedErrors = CollectionUtils.<ClembleCasinoError> immutableList(Arrays.asList(errors));
        return new CustomMatcher<ClembleCasinoException>(Arrays.toString(errors)) {

            @Override
            public boolean matches(Object item) {
                // Step 1. Sanity check
                if (!(item instanceof ClembleCasinoException))
                    return false;
                // Step 2. Checking value
                ClembleCasinoFailure failureDescription = ((ClembleCasinoException) item).getFailureDescription();
                if (failureDescription == null || (failureDescription.getServer().isEmpty() && failureDescription.getFields().isEmpty()))
                    return false;
                // Step 3. Accumulating errors
                for (ClembleCasinoError failure : failureDescription.getServer()) {
                    if (expectedErrors.contains(failure))
                        return true;
                }
                for (ClembleCasinoFieldError failure : failureDescription.getFields()) {
                    ClembleCasinoError error = ClembleCasinoError.forCode(failure.getCode());
                    if (expectedErrors.contains(error))
                        return true;
                }
                // Step 4. No possible errors found
                return false;
            }
        };

    }

    public static Matcher<ClembleCasinoException> fromErrors(final ClembleCasinoError... errors) {
        final Collection<ClembleCasinoError> expectedErrors = CollectionUtils.<ClembleCasinoError> immutableList(Arrays.asList(errors));
        return new CustomMatcher<ClembleCasinoException>(Arrays.toString(errors)) {

            @Override
            public boolean matches(Object item) {
                // Step 1. Unwrapping exception
                if (item instanceof ClembleCasinoServerException)
                    item = ((ClembleCasinoServerException) item).getCasinoException();
                if (!(item instanceof ClembleCasinoException))
                    return false;
                // Step 2. Checking value
                ClembleCasinoFailure failureDescription = ((ClembleCasinoException) item).getFailureDescription();
                if (failureDescription == null || (failureDescription.getServer().isEmpty() && failureDescription.getFields().isEmpty()))
                    return false;
                // Step 3. Accumulating errors
                Collection<ClembleCasinoError> actualErrors = new ArrayList<ClembleCasinoError>();
                for (ClembleCasinoError failure : failureDescription.getServer()) {
                    actualErrors.add(failure);
                }
                for (ClembleCasinoFieldError failure : failureDescription.getFields()) {
                    ClembleCasinoError error = ClembleCasinoError.forCode(failure.getCode());
                    actualErrors.add(error);
                }
                // Step 4. Checking errors
                return expectedErrors.containsAll(actualErrors) && actualErrors.containsAll(expectedErrors);
            }
        };
    }

}
