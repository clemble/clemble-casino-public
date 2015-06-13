package com.clemble.casino.payment;

import com.clemble.casino.KeyAware;

public interface PaymentTransactionAware extends KeyAware {

    String TRANSACTION_KEY = "transactionKey";

    String getTransactionKey();

}
