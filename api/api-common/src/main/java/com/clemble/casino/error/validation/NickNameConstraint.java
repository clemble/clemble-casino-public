package com.clemble.casino.error.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.clemble.casino.error.ClembleErrorCode.Code;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NickNameConstraintValidator.class)
public @interface NickNameConstraint {

    String message() default Code.NICK_INVALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
