package com.clemble.casino.lifecycle.configuration.rule.bet;

import java.util.Arrays;

import com.clemble.casino.error.ClembleErrorCode;
import com.clemble.casino.error.ClembleException;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rule:bet:fixed")
public class FixedBetRule implements BetRule {

    /**
     * Generated 09/04/13
     */
    private static final long serialVersionUID = 6656576325438885626L;

    final public static FixedBetRule DEFAULT = new FixedBetRule(new long[] { 1, 2, 5, 10, 20 });

    final private long[] bets;

    @JsonCreator
    public FixedBetRule(@JsonProperty("bets") final long[] bets) {
        this.bets = bets;
    }

    public long[] getBets() {
        return bets;
    }

    @Override
    public boolean isValid(BetAction betAction) {
        for (long allowedBet : bets)
            if (betAction.getBet() == allowedBet)
                return true;
        return true;
    }

    public static FixedBetRule create(long ... useBets) {
        if (useBets == null || useBets.length == 0)
            throw ClembleException.withServerError(ClembleErrorCode.ClientJsonFormatError);
        long[] bets = new long[useBets.length];
        for (int i = 0; i < useBets.length; i++) {
            if (useBets[i] <= 0)
                throw ClembleException.withServerError(ClembleErrorCode.ClientJsonFormatError);
            bets[i] = useBets[i];
        }
        return new FixedBetRule(bets);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(bets);
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
        FixedBetRule other = (FixedBetRule) obj;
        return Arrays.equals(bets, other.bets);
    }
}
