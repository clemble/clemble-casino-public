package com.clemble.casino.payment.event;

import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/16/14.
 */
@JsonTypeName(PaymentFreezeEvent.JSON_TYPE)
public class PaymentFreezeEvent implements PaymentEvent {

    final public static String JSON_TYPE = "payment:freeze";

    final private String player;
    final private Money amount;
    final private String transactionKey;

    @JsonCreator
    public PaymentFreezeEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty(TRANSACTION_KEY) String transactionKey,
        @JsonProperty("amount") Money amount) {
        this.player = player;
        this.amount = amount;
        this.transactionKey = transactionKey;
    }

    @Override
    public Money getAmount() {
        return amount;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentFreezeEvent that = (PaymentFreezeEvent) o;

        if (!amount.equals(that.amount)) return false;
        if (!player.equals(that.player)) return false;
        if (!transactionKey.equals(that.transactionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + transactionKey.hashCode();
        return result;
    }

}
