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

    public static Matcher<ClembleException> fromPossibleErrors(final ClembleErrorCode... errors) {
        final Collection<ClembleErrorCode> expectedErrors = CollectionUtils.<ClembleErrorCode> immutableList(Arrays.asList(errors));
        return new CustomMatcher<ClembleException>(Arrays.toString(errors)) {

            @Override
            public boolean matches(Object item) {
                // Step 1. Sanity check
                if (!(item instanceof ClembleException))
                    return false;
                // Step 2. Checking value
                ClembleError failureDescription = ((ClembleException) item).getFailureDescription();
                if (failureDescription == null || (failureDescription.getServer().isEmpty() && failureDescription.getFields().isEmpty()))
                    return false;
                // Step 3. Accumulating errors
                for (ClembleErrorCode failure : failureDescription.getServer()) {
                    if (expectedErrors.contains(failure))
                        return true;
                }
                for (ClembleFieldError failure : failureDescription.getFields()) {
                    if (expectedErrors.contains(failure.getCode()))
                        return true;
                }
                // Step 4. No possible errors found
                return false;
            }
        };

    }

    public static Matcher<ClembleException> fromErrors(final ClembleErrorCode... errors) {
        final Collection<ClembleErrorCode> expectedErrors = CollectionUtils.<ClembleErrorCode> immutableList(Arrays.asList(errors));
        return new CustomMatcher<ClembleException>(Arrays.toString(errors)) {

            @Override
            public boolean matches(Object item) {
                // Step 1. Unwrapping exception
                if (item instanceof ClembleServerException)
                    item = ((ClembleServerException) item).getCasinoException();
                if (!(item instanceof ClembleException))
                    return false;
                // Step 2. Checking value
                ClembleError failureDescription = ((ClembleException) item).getFailureDescription();
                if (failureDescription == null || (failureDescription.getServer().isEmpty() && failureDescription.getFields().isEmpty()))
                    return false;
                // Step 3. Accumulating errors
                Collection<ClembleErrorCode> actualErrors = new ArrayList<ClembleErrorCode>();
                for (ClembleErrorCode failure : failureDescription.getServer()) {
                    actualErrors.add(failure);
                }
                for (ClembleFieldError failure : failureDescription.getFields()) {
                    actualErrors.add(failure.getCode());
                }
                // Step 4. Checking errors
                return expectedErrors.containsAll(actualErrors) && actualErrors.containsAll(expectedErrors);
            }
        };
    }

}
