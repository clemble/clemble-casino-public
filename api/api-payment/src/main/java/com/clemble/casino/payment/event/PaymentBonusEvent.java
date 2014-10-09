package com.clemble.casino.payment.event;

import com.clemble.casino.payment.AmountAware;
import com.clemble.casino.payment.bonus.PaymentBonusSource;
import com.clemble.casino.payment.bonus.PaymentBonusSourceAware;
import com.clemble.casino.money.Money;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PaymentBonusEvent.JSON_TYPE)
public class PaymentBonusEvent implements PaymentEvent, PlayerAware, AmountAware, PaymentBonusSourceAware {

    final public static String JSON_TYPE = "payment:bonus";

    /**
     * Generated 16/12/13
     */
    private static final long serialVersionUID = -8235452183888519660L;

    final private String player;
    final private Money amount;
    final private PaymentBonusSource bonusSource;
    final private String transactionKey;

    @JsonCreator
    public PaymentBonusEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("amount") Money money,
        @JsonProperty("bonusSource") PaymentBonusSource bonusSource,
        @JsonProperty(TRANSACTION_KEY) String transactionKey) {
        this.player = player;
        this.amount = money;
        this.bonusSource = bonusSource;
        this.transactionKey = transactionKey;
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
    public String getTransactionKey() {
        return transactionKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((bonusSource == null) ? 0 : bonusSource.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PaymentBonusEvent other = (PaymentBonusEvent) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (bonusSource != other.bonusSource)
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return transactionKey + " > " + JSON_TYPE + " > " + bonusSource + ":" + player + ":" + amount;
    }

}
