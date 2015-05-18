package com.clemble.casino.error.validation;

import com.clemble.casino.error.ClembleErrorCode;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by mavarazy on 5/4/15.
 */
@PasswordConstraint // TODO unite all requirements for password in @PasswordConstraint
@MinSize(min = 6, message = ClembleErrorCode.Code.PASSWORD_TOO_SHORT)
@MaxSize(max = 64, message = ClembleErrorCode.Code.PASSWORD_TOO_LONG)
@NotNull(message = ClembleErrorCode.Code.PASSWORD_MISSING)
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Constraint(validatedBy = { })
public @interface ClemblePasswordConstraint {

    String message() default "{com.clemble.casino.error.validation.ClemblePasswordConstraint}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
