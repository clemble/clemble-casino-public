package com.clemble.casino.error.validation;

import com.clemble.casino.error.ClembleErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mavarazy on 2/16/15.
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoWhiteSpacesValidator.class)
public @interface NoWhiteSpaces {
    String message() default ClembleErrorCode.Code.NICK_INVALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
