package com.clemble.casino.payment;

import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 15/10/14.
 */
public class PendingOperation implements PaymentTransactionAware, AmountAware {

    final private Money amount;
    final private String transactionKey;

    @JsonCreator
    public PendingOperation(
        @JsonProperty("transactionKey") String transactionKey,
        @JsonProperty("amount") Money amount) {
        this.transactionKey = transactionKey;
        this.amount = amount;
    }

    @Override
    public String getTransactionKey() {
        return transactionKey;
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

        if (!amount.equals(that.amount)) return false;
        if (!transactionKey.equals(that.transactionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = transactionKey.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

}
