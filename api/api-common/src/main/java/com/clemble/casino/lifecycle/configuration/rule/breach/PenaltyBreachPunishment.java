package com.clemble.casino.lifecycle.configuration.rule.breach;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.TimeoutPunishmentAction;
import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 8/30/14.
 */
@JsonTypeName("penalty")
public class PenaltyBreachPunishment extends BreachPunishment implements AmountAware {

    final private Money amount;

    @JsonCreator
    public PenaltyBreachPunishment(@JsonProperty("amount") Money amount) {
        this.amount = amount;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    @Override
    public PlayerAction toBreachEvent(String player) {
        return new TimeoutPunishmentAction(player, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PenaltyBreachPunishment that = (PenaltyBreachPunishment) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return amount != null ? amount.hashCode() : 0;
    }
}
