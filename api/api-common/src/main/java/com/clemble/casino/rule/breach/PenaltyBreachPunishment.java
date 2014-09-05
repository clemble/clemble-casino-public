package com.clemble.casino.rule.breach;

import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 8/30/14.
 */
@JsonTypeName("penalty")
public class PenaltyBreachPunishment extends BreachPunishment {

    final private Money amount;

    @JsonCreator
    public PenaltyBreachPunishment(@JsonProperty("amount") Money amount) {
        this.amount = amount;
    }

    public Money getAmount() {
        return amount;
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
