package com.clemble.casino.payment.notification;

import com.clemble.casino.money.Money;
import com.clemble.casino.money.Operation;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.*;
import com.clemble.casino.payment.event.PaymentCompleteEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
public class PaymentNotification implements
    PlayerNotification,
    AmountAware,
    // PaymentTransactionAware,
    PaymentSourceAware {

    final public static String JSON_TYPE = "notification:payment";

    final private String player;
    final private String key;
    final private Money amount;
    final private Operation operation;
    final private PaymentSource source;
    final private Date created;

    @JsonCreator
    public PaymentNotification(
        @JsonProperty("key") String key,
        @JsonProperty(PLAYER) String player,
        @JsonProperty("amount") Money amount,
        @JsonProperty("operation") Operation operation,
        @JsonProperty("source") PaymentSource source,
        @JsonProperty("created") Date created) {
        this.key = key;
        this.created = created;
        this.player = player;
        this.amount = amount;
        this.operation = operation;
        this.source = source;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public PaymentSource getSource() {
        return source;
    }

    public static PaymentNotification create(PaymentCompleteEvent completeEvent) {
        return new PaymentNotification(
            completeEvent.getTransactionKey(),
            completeEvent.getPlayer(),
            completeEvent.getAmount(),
            completeEvent.getOperation(),
            completeEvent.getSource(),
            new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentNotification that = (PaymentNotification) o;

        if (!amount.equals(that.amount)) return false;
        if (operation != that.operation) return false;
        if (!player.equals(that.player)) return false;
        if (!source.equals(that.source)) return false;
        if (!key.equals(that.key)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + key.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + operation.hashCode();
        result = 31 * result + source.hashCode();
        return result;
    }

}
