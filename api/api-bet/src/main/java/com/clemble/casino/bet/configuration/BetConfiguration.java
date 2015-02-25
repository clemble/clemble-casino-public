package com.clemble.casino.bet.configuration;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.rule.bet.BetRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 9/6/14.
 */
public class BetConfiguration implements Configuration {

    final private BetRule betRule;

    @JsonCreator
    public BetConfiguration(
        @JsonProperty("betRule") BetRule betRule) {
        this.betRule = betRule;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetConfiguration that = (BetConfiguration) o;

        return that.betRule.equals(this.betRule);
    }

    @Override
    public int hashCode() {
        return betRule.hashCode();
    }

}
