package com.clemble.casino.payment.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.clemble.casino.error.ClembleCasinoError.Code;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DebitMatchCreditConstraintValidator.class)
public @interface DebitMatchCreditConstraint {

    String message() default Code.PAYMENT_TRANSACTION_DEBIT_AND_CREDIT_NOT_MATCHED;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
