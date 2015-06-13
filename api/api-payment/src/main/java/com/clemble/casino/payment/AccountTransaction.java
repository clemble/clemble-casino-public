package com.clemble.casino.payment;

import com.clemble.casino.money.Currency;
import com.clemble.casino.payment.validation.DebitMatchCreditConstraint;

import java.util.Set;

/**
 * Created by mavarazy on 16/10/14.
 */
public interface AccountTransaction extends PaymentTransactionAware{

    @DebitMatchCreditConstraint
    Set<PaymentOperation> getOperations();

    PaymentOperation getOperation(String player, Currency currency);

}
