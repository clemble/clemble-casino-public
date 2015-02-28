package com.clemble.casino.lifecycle.management.event.action.bet;

import com.clemble.casino.lifecycle.management.event.action.Action;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(BetAction.JSON_TYPE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BetAction implements Action {

    final public static String JSON_TYPE = "player:bet:action";

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 4761116695040560749L;

    final private int bet;

    @JsonCreator
    public BetAction(@JsonProperty("bet") int bet) {
        this.bet = bet;
        if (bet < 0)
            throw new IllegalArgumentException("Bet can't be lesser than 0");
    }

    public int getBet() {
        return bet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (bet ^ (bet >>> 32));
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
        BetAction other = (BetAction) obj;
        if (bet != other.bet)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return JSON_TYPE + " > " + bet;
    }

}
