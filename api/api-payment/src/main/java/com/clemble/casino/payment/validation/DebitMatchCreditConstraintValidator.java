package com.clemble.casino.payment.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;

public class DebitMatchCreditConstraintValidator implements ConstraintValidator<DebitMatchCreditConstraint, Set<PaymentOperation>> {

    final private static Logger LOG = LoggerFactory.getLogger(DebitMatchCreditConstraint.class);

    @Override
    public void initialize(DebitMatchCreditConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Set<PaymentOperation> paymentOperations, ConstraintValidatorContext context) {
        // Step 1. Separating by currency
        Map<Currency, Set<PaymentOperation>> currencyToOperations = new HashMap<Currency, Set<PaymentOperation>>();
        for (PaymentOperation operation : paymentOperations) {
            Currency currency = operation.getAmount().getCurrency();
            if (currencyToOperations.get(currency) == null)
                currencyToOperations.put(currency, new HashSet<PaymentOperation>());
            currencyToOperations.get(operation.getAmount().getCurrency()).add(operation);
        }
        // Step 2. Checking each currency
        boolean isValid = true;
        for(Map.Entry<Currency, Set<PaymentOperation>> currencyToOperation: currencyToOperations.entrySet()) {
            isValid = isValid(currencyToOperation.getKey(), currencyToOperation.getValue(), context) && isValid;
        }
        return isValid;
    }

    public boolean isValid(Currency currency, Set<PaymentOperation> paymentOperations, ConstraintValidatorContext context) {
        // Step 1. Checking currency
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
