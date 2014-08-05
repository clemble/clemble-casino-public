package com.clemble.casino.payment.service;

import java.util.List;

import com.clemble.casino.payment.PaymentTransaction;

public interface PaymentTransactionService extends PaymentTransactionServiceContract {

    public List<PaymentTransaction> myTransactions();

    public List<PaymentTransaction> myTransactions(String source);

}
