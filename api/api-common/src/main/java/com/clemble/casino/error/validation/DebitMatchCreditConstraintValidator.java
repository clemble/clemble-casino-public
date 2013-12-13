package com.clemble.casino.error.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.payment.money.Money;

public class DebitMatchCreditConstraintValidator implements ConstraintValidator<DebitMatchCreditConstraint, Set<PaymentOperation>>{

    @Override
    public void initialize(DebitMatchCreditConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<PaymentOperation> paymentOperations, ConstraintValidatorContext context) {
        // Step 1. Checking currency
        Currency currency = null;
        for (PaymentOperation paymentOperation : paymentOperations) {
            if (currency == null) {
                currency = paymentOperation.getAmount().getCurrency();
            } else if (currency != paymentOperation.getAmount().getCurrency()) {
                return false;
            }
        }
        // Step 2. Checking credit and debit amount match up
        Money creditAmount = null;
        Money debitAmount = null;
        for (PaymentOperation paymentOperation : paymentOperations) {
            Money amount = paymentOperation.getAmount();
            if (amount.getAmount() > 0) {
                switch (paymentOperation.getOperation()) {
                case Credit:
                    creditAmount = creditAmount == null ? paymentOperation.getAmount() : creditAmount.add(amount);
                    break;
                case Debit:
                    debitAmount = debitAmount == null ? paymentOperation.getAmount() : debitAmount.add(paymentOperation.getAmount());
                    break;
                }
            } else {
                amount = amount.negate();
                switch (paymentOperation.getOperation()) {
                case Credit:
                    debitAmount = debitAmount == null ? paymentOperation.getAmount() : debitAmount.add(paymentOperation.getAmount());
                    break;
                case Debit:
                    creditAmount = creditAmount == null ? paymentOperation.getAmount() : creditAmount.add(amount);
                    break;
                }
            }
        }
        return (creditAmount != null && debitAmount != null)
                && creditAmount.getAmount() == debitAmount.getAmount()
                && creditAmount.getCurrency() == debitAmount.getCurrency();
    }

}
