package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;

/**
 * Created by mavarazy on 11/20/14.
 */
@JsonTypeName("rule:bet:bid:fixed")
public class FixedBidRule implements BidRule {

    final private Bet[] bets;

    @JsonCreator
    public FixedBidRule(@JsonProperty("bids") Bet[] bets) {
        this.bets = bets;
    }

    public Bet[] getBets() {
        return bets;
    }

    public static FixedBidRule create(Bet... bets) {
        return new FixedBidRule(bets);
    }

    @Override
    public boolean isValid(BetAction betAction) {
        for (Bet bet : bets) {
            if (bet.getAmount().getAmount() == betAction.getBet())
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixedBidRule that = (FixedBidRule) o;

        return Arrays.equals(bets, that.bets);

    }

    @Override
    public int hashCode() {
        return bets != null ? Arrays.hashCode(bets) : 0;
    }
}
