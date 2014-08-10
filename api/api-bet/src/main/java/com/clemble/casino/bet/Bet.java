package com.clemble.casino.bet;

import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.payment.PaymentOperation;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mavarazy on 8/9/14.
 */
public class Bet implements BetAware {

    final private String betKey;
    final private Collection<Bid> bids;

    @JsonCreator
    public Bet(@JsonProperty("betKey") String betKey, @JsonProperty("bids")  Collection<Bid> bids) {
        this.betKey = betKey;
        this.bids = bids;
    }

    public Collection<Bid> getBids() {
        return bids;
    }

    public String getBetKey(){
        return betKey;
    }

    @Override
    public String toBetKey() {
        return betKey;
    }

    public PaymentTransaction toTransaction(String player) {
        // Step 1. Converting bids to operations
        // TODO Be careful of duplicates
        Money balance = Money.create(Currency.FakeMoney, 0);
        Set<PaymentOperation> operations = new HashSet<PaymentOperation>();
        for(Bid bid: bids) {
            if(!bid.getPlayer().equals(player)) {
                operations.add(new PaymentOperation(bid.getBidder(), bid.getBidAmount(), Operation.Credit));
                balance.add(bid.getBidAmount());
            } else {
                operations.add(new PaymentOperation(bid.getBidder(), bid.getAmount(), Operation.Debit));
                balance.subtract(bid.getAmount());
            }
        }
        // Step 2. Adding casino to compensate Debit & Credit
        if(balance.getAmount() != 0) {
            operations.add(new PaymentOperation(PlayerAware.DEFAULT_PLAYER, balance, Operation.Debit));
        }
        // Step 3. Creating new transaction
        return new PaymentTransaction().setPaymentOperations(operations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (!betKey.equals(bet.betKey)) return false;
        if (!bids.equals(bet.bids)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = betKey.hashCode();
        result = 31 * result + bids.hashCode();
        return result;
    }
}
