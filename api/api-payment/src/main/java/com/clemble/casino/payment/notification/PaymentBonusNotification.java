package com.clemble.casino.payment.notification;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.bonus.PaymentBonusSource;
import com.clemble.casino.payment.bonus.PaymentBonusSourceAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PaymentBonusNotification.JSON_TYPE)
public class PaymentBonusNotification implements PaymentNotification, PaymentBonusSourceAware {

    final public static String JSON_TYPE = "notification:payment:bonus";

    final private String player;
    final private PaymentBonusSource bonusSource;
    final private Money amount;

    @JsonCreator
    public PaymentBonusNotification(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("bonusSource") PaymentBonusSource source,
        @JsonProperty("amount") Money amount) {
        this.player = player;
        this.amount = amount;
        this.bonusSource = source;
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
    public PaymentBonusSource getBonusSource() {
        return bonusSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentBonusNotification that = (PaymentBonusNotification) o;

        if (!amount.equals(that.amount)) return false;
        if (bonusSource != that.bonusSource) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bonusSource.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

}
