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
    final private BetSpecification specification;
    final private Collection<PlayerBid> playerBids;

    @JsonCreator
    public BetPaymentTransaction(
        @JsonProperty(TRANSACTION_KEY) PaymentTransactionKey transactionKey,
        @JsonProperty("specification") BetSpecification specification,
        @JsonProperty("bids") Collection<PlayerBid> bids) {
        this.transactionKey = transactionKey;
        this.specification = specification;
        this.playerBids = bids;
    }

    public BetSpecification getSpecification() {
        return specification;
    }

    public Collection<PlayerBid> getPlayerBids() {
        return playerBids;
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
        for(PlayerBid bid: playerBids) {
            if(!bid.getWinner().equals(winner)) {
                transaction.addPaymentOperation(new PaymentOperation(bid.getBidder(), bid.getBid().getAmount(), Operation.Credit));
                balance.add(bid.getBid().getAmount());
            } else {
                Money amountWithInterest = bid.getBid().getAmount().add(bid.getBid().getInterest());
                transaction.addPaymentOperation(new PaymentOperation(bid.getBidder(), amountWithInterest, Operation.Debit));
                balance.subtract(amountWithInterest);
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
        if (!playerBids.equals(bet.playerBids)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionKey.hashCode();
        result = 31 * result + playerBids.hashCode();
        return result;
    }
}
