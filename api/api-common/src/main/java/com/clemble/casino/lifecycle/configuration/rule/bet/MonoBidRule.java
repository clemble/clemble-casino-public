package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.BidAware;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/22/14.
 */
@JsonTypeName("rule:bet:bid:mono")
public class MonoBidRule implements BidRule, BidAware {

    final private Bid bid;

    @JsonCreator
    public MonoBidRule(@JsonProperty("bid") Bid bid) {
        this.bid = bid;
    }

    @Override
    public Bid getBid() {
        return bid;
    }

    @Override
    public boolean isValid(BetAction betAction) {
        return betAction.getBet() == bid.getAmount().getAmount();
    }

    public static MonoBidRule create(Bid bid) {
        return new MonoBidRule(bid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonoBidRule that = (MonoBidRule) o;

        if (!bid.equals(that.bid)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bid.hashCode();
    }

}
