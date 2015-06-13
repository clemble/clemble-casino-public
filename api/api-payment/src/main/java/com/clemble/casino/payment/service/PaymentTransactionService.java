package com.clemble.casino.payment.service;

import java.util.List;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PaymentService;
import com.clemble.casino.payment.PaymentTransaction;

public interface PaymentTransactionService extends PaymentService {

    List<PaymentTransaction> myTransactions();

    List<PaymentTransaction> myTransactionsBySource(String source);

    PaymentTransaction getTransaction(String transactionKey);

    List<PaymentTransaction> getPlayerTransactions(String player);

}
