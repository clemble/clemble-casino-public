package com.clemble.casino.bet.configuration;

import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.rule.bet.BetRule;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/6/14.
 */
public class BetConfiguration implements Configuration {

    final private BetRule betRule;
    final private PrivacyRule privacyRule;

    @JsonCreator
    public BetConfiguration(
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("betRule") BetRule betRule) {
        this.privacyRule = privacyRule;
        this.betRule = betRule;
    }

    public BetRule getBetRule() {
        return betRule;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetConfiguration that = (BetConfiguration) o;

        if (privacyRule != that.privacyRule) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return privacyRule.hashCode();
    }

}
