package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.bet.Bid;
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

    final private Bid[] bids;

    @JsonCreator
    public FixedBidRule(@JsonProperty("bids") Bid[] bids) {
        this.bids = bids;
    }

    public Bid[] getBids() {
        return bids;
    }

    public static FixedBidRule create(Bid ... bids) {
        return new FixedBidRule(bids);
    }

    @Override
    public boolean isValid(BetAction betAction) {
        for (Bid bid : bids) {
            if (bid.getAmount().getAmount() == betAction.getBet())
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FixedBidRule that = (FixedBidRule) o;

        if (!Arrays.equals(bids, that.bids)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bids != null ? Arrays.hashCode(bids) : 0;
    }
}
