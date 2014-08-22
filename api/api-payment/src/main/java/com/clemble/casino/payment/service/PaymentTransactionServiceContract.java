package com.clemble.casino.payment.service;

import java.util.List;

import com.clemble.casino.ClembleService;
import com.clemble.casino.payment.PaymentTransaction;

public interface PaymentTransactionServiceContract extends ClembleService {

    public PaymentTransaction getTransaction(String transactionKey);

    public List<PaymentTransaction> getPlayerTransactions(String player);

}
