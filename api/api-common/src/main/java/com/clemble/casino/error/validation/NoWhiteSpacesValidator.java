package com.clemble.casino.error.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by mavarazy on 2/16/15.
 */
public class NoWhiteSpacesValidator implements ConstraintValidator<NoWhiteSpaces, String>{

    final private Pattern WHITE_SPACES = Pattern.compile("\\s");

    @Override
    public void initialize(NoWhiteSpaces constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // TODO consider CharMatcher.WHITESPACE.matchesAnyOf(testCode);
        return value != null && !WHITE_SPACES.matcher(value).matches();
    }

}
