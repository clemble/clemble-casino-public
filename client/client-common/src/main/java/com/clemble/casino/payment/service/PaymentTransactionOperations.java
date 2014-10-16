package com.clemble.casino.payment.service;

import com.clemble.casino.game.Game;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.bonus.PaymentBonusSource;

import java.util.List;

/**
 * Created by mavarazy on 8/5/14.
 */
public class PaymentTransactionOperations implements PaymentTransactionService {

    final private PaymentTransactionService delegate;

    public PaymentTransactionOperations(PaymentTransactionService transactionService) {
        this.delegate = transactionService;
    }

    @Override
    public List<PaymentTransaction> myTransactions() {
        return delegate.myTransactions();
    }

    @Override
    public List<PaymentTransaction> myTransactionsBySource(String source) {
        return delegate.myTransactionsBySource(source);
    }

    public List<PaymentTransaction> myTransactions(Game game) {
        return delegate.myTransactionsBySource(game.name());
    }

    public List<PaymentTransaction> myTransactions(PaymentBonusSource source) {
        return delegate.myTransactionsBySource(source.name());
    }

    public PaymentTransaction getTransaction(String sessionKey) {
        return delegate.getTransaction(sessionKey);
    }

    @Override
    public List<PaymentTransaction> getPlayerTransactions(String player) {
        return delegate.getPlayerTransactions(player);
    }

}
