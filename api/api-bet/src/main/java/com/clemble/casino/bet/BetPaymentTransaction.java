package com.clemble.casino.bet;

import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PaymentTransactionAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Created by mavarazy on 8/9/14.
 */
public class BetPaymentTransaction implements PaymentTransactionAware, PlayerBetAware {

    final private String transactionKey;
    final private BetSpecification specification;
    final private Collection<PlayerBet> bets;

    @JsonCreator
    public BetPaymentTransaction(
        @JsonProperty(TRANSACTION_KEY) String transactionKey,
        @JsonProperty("specification") BetSpecification specification,
        @JsonProperty("bets") Collection<PlayerBet> bets) {
        this.transactionKey = transactionKey;
        this.specification = specification;
        this.bets = bets;
    }

    public BetSpecification getSpecification() {
        return specification;
    }

    @Override
    public Collection<PlayerBet> getBets() {
        return bets;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
    }

    public PaymentTransaction toTransaction(String winner) {
        // Step 1. Converting bids to operations
        Money balance = Money.create(Currency.point, 0);
        PaymentTransaction transaction = new PaymentTransaction().
            setTransactionKey(transactionKey);
        for(PlayerBet bet: bets) {
            if(!bet.getPlayer().equals(winner)) {
                transaction.addOperation(new PaymentOperation(bet.getPlayer(), bet.getBet().getAmount(), Operation.Credit));
                balance.add(bet.getBet().getAmount());
            } else {
                Money amountWithInterest = bet.getBet().getAmount().add(bet.getBet().getInterest());
                transaction.addOperation(new PaymentOperation(bet.getPlayer(), amountWithInterest, Operation.Debit));
                balance.subtract(amountWithInterest);
            }
        }
        // Step 2. Adding casino to compensate Debit & Credit
        if(balance.getAmount() != 0) {
            transaction.addOperation(new PaymentOperation(PlayerAware.DEFAULT_PLAYER, balance, Operation.Debit));
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
        return bets.equals(bet.bets);

    }

    @Override
    public int hashCode() {
        int result = transactionKey.hashCode();
        result = 31 * result + bets.hashCode();
        return result;
    }
}
