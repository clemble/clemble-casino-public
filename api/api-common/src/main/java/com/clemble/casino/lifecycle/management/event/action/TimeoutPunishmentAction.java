package com.clemble.casino.lifecycle.management.event.action;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 13/10/14.
 */
@JsonTypeName(TimeoutPunishmentAction.JSON_TYPE)
public class TimeoutPunishmentAction implements PlayerAction, AmountAware {

    final public static String JSON_TYPE = "timeout:punishment:action";

    final private String player;
    final private Money amount;

    @JsonCreator
    public TimeoutPunishmentAction(
            @JsonProperty(PLAYER) String player,
            @JsonProperty("amount") Money amount) {
        this.player = player;
        this.amount = amount;
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
        if (!(o instanceof TimeoutPunishmentAction)) return false;

        TimeoutPunishmentAction that = (TimeoutPunishmentAction) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

}
