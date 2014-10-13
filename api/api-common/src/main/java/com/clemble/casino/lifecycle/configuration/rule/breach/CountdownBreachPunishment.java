package com.clemble.casino.lifecycle.configuration.rule.breach;

import com.clemble.casino.lifecycle.management.event.action.Action;
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
@JsonTypeName("countdown")
public class CountdownBreachPunishment extends BreachPunishment implements AmountAware {

    final private Money amount;
    final private long timeInMinutes;

    @JsonCreator
    public CountdownBreachPunishment(@JsonProperty("amount") Money amount, @JsonProperty("timeInMinutes") long timeInMinutes) {
        this.amount = amount;
        this.timeInMinutes = timeInMinutes;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    public long getTimeInMinutes() {
        return timeInMinutes;
    }

    @Override
    public Action toBreachEvent() {
        return new TimeoutPunishmentAction(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountdownBreachPunishment that = (CountdownBreachPunishment) o;

        if (timeInMinutes != that.timeInMinutes) return false;
        if (!amount.equals(that.amount)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + (int) (timeInMinutes ^ (timeInMinutes >>> 32));
        return result;
    }

}
