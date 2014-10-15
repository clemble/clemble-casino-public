package com.clemble.casino.payment;

import com.clemble.casino.money.Money;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 15/10/14.
 */
public class PendingOperation implements PlayerAware, PaymentTransactionAware, AmountAware {

    final private Money amount;
    final private String player;
    final private String transactionKey;

    @JsonCreator
    public PendingOperation(
        @JsonProperty("player") String player,
        @JsonProperty("transactionKey") String transactionKey,
        @JsonProperty("amount") Money amount) {
        this.amount = amount;
        this.player = player;
        this.transactionKey = transactionKey;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingOperation)) return false;

        PendingOperation that = (PendingOperation) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (transactionKey != null ? !transactionKey.equals(that.transactionKey) : that.transactionKey != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (transactionKey != null ? transactionKey.hashCode() : 0);
        return result;
    }

}
