package com.clemble.casino.error.validation;

import com.clemble.casino.error.ClembleCasinoError;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mavarazy on 2/16/15.
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoWhiteSpacesValidator.class)
public @interface NoWhiteSpaces {
    String message() default ClembleCasinoError.Code.NICK_INVALID_CODE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
