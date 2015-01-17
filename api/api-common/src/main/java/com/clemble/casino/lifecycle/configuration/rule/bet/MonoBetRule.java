package com.clemble.casino.lifecycle.configuration.rule.bet;

import com.clemble.casino.bet.Bet;
import com.clemble.casino.bet.BetAware;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/22/14.
 */
@JsonTypeName("rule:bet:bid:mono")
public class MonoBetRule implements BidRule, BetAware {

    final private Bet bet;

    @JsonCreator
    public MonoBetRule(@JsonProperty("bid") Bet bet) {
        this.bet = bet;
    }

    @Override
    public Bet getBet() {
        return bet;
    }

    @Override
    public boolean isValid(BetAction betAction) {
        return betAction.getBet() == bet.getAmount().getAmount();
    }

    public static MonoBetRule create(Bet bet) {
        return new MonoBetRule(bet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonoBetRule that = (MonoBetRule) o;

        if (!bet.equals(that.bet)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return bet.hashCode();
    }

}
