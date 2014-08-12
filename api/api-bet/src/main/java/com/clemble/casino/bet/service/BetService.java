package com.clemble.casino.bet.service;

import com.clemble.casino.bet.BetPaymentTransaction;
import com.clemble.casino.payment.PaymentTransactionKey;

import java.util.List;

/**
 * Created by mavarazy on 8/9/14.
 */
public interface BetService {

    public List<BetPaymentTransaction> myBets();

    public BetPaymentTransaction getBet(PaymentTransactionKey transaction);

}
