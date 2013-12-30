package com.clemble.casino.error.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.payment.money.Money;

public class DebitMatchCreditConstraintValidator implements ConstraintValidator<DebitMatchCreditConstraint, Set<PaymentOperation>> {

    final private static Logger LOG = LoggerFactory.getLogger(DebitMatchCreditConstraint.class);

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
                LOG.error("Currencies do not match expected {}, but got {}", currency, paymentOperation.getAmount().getCurrency());
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
        if(creditAmount == null || debitAmount == null) {
            LOG.error("Debit is {}, while credit is {}", creditAmount, debitAmount);
            return false;
        }
        if(creditAmount.getAmount() != debitAmount.getAmount()) {
            LOG.error("Debit amount is {}, while credit amount is {}", creditAmount.getAmount(), debitAmount.getAmount());
            return false;
        }
        if(creditAmount.getCurrency() != debitAmount.getCurrency()) {
            LOG.error("Debit currency is {}, while credit currency is {}", creditAmount.getCurrency(), debitAmount.getCurrency());
            return false;
        }
        return true;
    }

}
