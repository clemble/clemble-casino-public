package com.clemble.casino.payment;

import com.clemble.casino.KeyAware;

public interface PaymentTransactionAware extends KeyAware {

    final public static String TRANSACTION_KEY = "transactionKey";

    public String getTransactionKey();

}
