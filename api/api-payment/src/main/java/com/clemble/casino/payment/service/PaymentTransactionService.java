package com.clemble.casino.payment.service;

import java.util.List;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PaymentService;
import com.clemble.casino.payment.PaymentTransaction;

public interface PaymentTransactionService extends PaymentService {

    public List<PaymentTransaction> myTransactions();

    public List<PaymentTransaction> myTransactionsBySource(String source);

    public PaymentTransaction getTransaction(String transactionKey);

    public List<PaymentTransaction> getPlayerTransactions(String player);

}
