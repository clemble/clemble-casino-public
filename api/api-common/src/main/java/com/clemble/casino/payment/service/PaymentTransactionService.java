package com.clemble.casino.payment.service;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.payment.bonus.PaymentBonusSource;

public interface PaymentTransactionService extends PaymentTransactionServiceContract {

    public List<PaymentTransaction> myTransactions();

    public List<PaymentTransaction> myTransactions(String source);

}
