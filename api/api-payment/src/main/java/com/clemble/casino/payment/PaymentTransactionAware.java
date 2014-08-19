package com.clemble.casino.payment;

import com.clemble.casino.KeyAware;

public interface PaymentTransactionAware extends KeyAware {

    public PaymentTransactionKey getTransactionKey();

}
