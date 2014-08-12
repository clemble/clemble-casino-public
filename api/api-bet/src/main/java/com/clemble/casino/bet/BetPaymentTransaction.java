package com.clemble.casino.bet;

import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PaymentTransactionAware;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by mavarazy on 8/9/14.
 */
public class BetPaymentTransaction implements PaymentTransactionAware {

    final private PaymentTransactionKey transactionKey;
    final private Collection<Bid> bids;

    @JsonCreator
    public BetPaymentTransaction(@JsonProperty("transactionKey") PaymentTransactionKey transactionKey, @JsonProperty("bids") Collection<Bid> bids) {
        this.transactionKey = transactionKey;
        this.bids = bids;
    }

    public Collection<Bid> getBids() {
        return bids;
    }

    @Override
    public PaymentTransactionKey getTransactionKey() {
        return transactionKey;
    }

    public PaymentTransaction toTransaction(String winner) {
        // Step 1. Converting bids to operations
        Money balance = Money.create(Currency.FakeMoney, 0);
        PaymentTransaction transaction = new PaymentTransaction().
            setTransactionKey(transactionKey);
        for(Bid bid: bids) {
            if(!bid.getWinner().equals(winner)) {
                transaction.addPaymentOperation(new PaymentOperation(bid.getBidder(), bid.getBidAmount(), Operation.Credit));
                balance.add(bid.getBidAmount());
            } else {
                transaction.addPaymentOperation(new PaymentOperation(bid.getBidder(), bid.getAmount(), Operation.Debit));
                balance.subtract(bid.getAmount());
            }
        }
        // Step 2. Adding casino to compensate Debit & Credit
        if(balance.getAmount() != 0) {
            transaction.addPaymentOperation(new PaymentOperation(PlayerAware.DEFAULT_PLAYER, balance, Operation.Debit));
        }
        // Step 3. Creating new transaction
        return transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetPaymentTransaction bet = (BetPaymentTransaction) o;

        if (!transactionKey.equals(bet.transactionKey)) return false;
        if (!bids.equals(bet.bids)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionKey.hashCode();
        result = 31 * result + bids.hashCode();
        return result;
    }
}
