package com.clemble.casino.payment;

/**
 * Created by mavarazy on 8/10/14.
 */
public interface PaymentTransactionConvertible extends PaymentTransactionAware {

    public PaymentTransaction toTransaction();

}
