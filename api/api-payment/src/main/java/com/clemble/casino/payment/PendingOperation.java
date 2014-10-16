package com.clemble.casino.payment;

import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 15/10/14.
 */
public class PendingOperation implements AccountOperation, PaymentTransactionAware {

    final private String player;
    final private Money amount;
    final private Operation operation;
    final private String transactionKey;

    @JsonCreator
    public PendingOperation(
        @JsonProperty("transactionKey") String transactionKey,
        @JsonProperty("player") String player,
        @JsonProperty("amount") Money amount,
        @JsonProperty("operation") Operation operation) {
        this.player = player;
        this.amount = amount;
        this.operation = operation;
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
    public Operation getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "pending:" + operation + ":" + player + ":" + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingOperation)) return false;

        PendingOperation that = (PendingOperation) o;

        if (!amount.equals(that.amount)) return false;
        if (operation != that.operation) return false;
        if (!player.equals(that.player)) return false;
        if (!transactionKey.equals(that.transactionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + operation.hashCode();
        result = 31 * result + transactionKey.hashCode();
        return result;
    }

    public static PendingOperation fromOperation(String transactionKey, AccountOperation operation) {
        return new PendingOperation(transactionKey, operation.getPlayer(), operation.getAmount(), operation.getOperation());
    }

}
