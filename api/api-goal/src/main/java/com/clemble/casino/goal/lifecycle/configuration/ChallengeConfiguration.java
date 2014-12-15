package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/15/14.
 */
public class ChallengeConfiguration {

    final private Bid bid;
    final private BetRule betRule;
    final private TotalTimeRule totalTimeRule;

    @JsonCreator
    public ChallengeConfiguration(
        @JsonProperty("bid") Bid bid,
        @JsonProperty("betRule") BetRule betRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule
    ) {
        this.bid = bid;
        this.betRule = betRule;
        this.totalTimeRule = totalTimeRule;
    }

    public Bid getBid() {
        return bid;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChallengeConfiguration that = (ChallengeConfiguration) o;

        if (!betRule.equals(that.betRule)) return false;
        if (!bid.equals(that.bid)) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid.hashCode();
        result = 31 * result + betRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        return result;
    }

}
