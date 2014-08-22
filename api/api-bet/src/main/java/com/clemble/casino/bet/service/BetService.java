package com.clemble.casino.bet.service;

import com.clemble.casino.bet.BetPaymentTransaction;
import com.clemble.casino.bet.BetSpecification;

import java.util.List;

/**
 * Created by mavarazy on 8/9/14.
 */
public interface BetService {

    public List<BetPaymentTransaction> myBets();

    public BetSpecification getBetSpecification(String transactionKey);

    public BetPaymentTransaction getBet(String transaction);

}
