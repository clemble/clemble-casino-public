package com.clemble.casino.payment.service;

import java.util.List;

import com.clemble.casino.payment.PaymentTransaction;

public interface PaymentTransactionService {

    public PaymentTransaction getTransaction(String source, String transactionId);

    public List<PaymentTransaction> getPlayerTransactions(String player);

    public List<PaymentTransaction> getPlayerTransactionsWithSource(String player, String source);

}
