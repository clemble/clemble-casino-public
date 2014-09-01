package com.clemble.casino.goal.configuration;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.goal.rule.privacy.GoalPrivacyRule;
import com.clemble.casino.goal.rule.status.GoalStatusRule;
import com.clemble.casino.goal.rule.time.GoalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConfiguration {

    final private Bid bid;
    final private GoalTimeRule timeRule;
    final private GoalPrivacyRule privacyRule;
    final private GoalStatusRule statusRule;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("bid") Bid bid,
        @JsonProperty("timeRule") GoalTimeRule timeRule,
        @JsonProperty("privacyRule") GoalPrivacyRule privacyRule,
        @JsonProperty("statusRule") GoalStatusRule statusRule) {
        this.bid = bid;
        this.timeRule = timeRule;
        this.privacyRule = privacyRule;
        this.statusRule = statusRule;
    }

    public Bid getBid() {
        return bid;
    }

    public GoalTimeRule getTimeRule() {
        return timeRule;
    }

    public GoalPrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    public GoalStatusRule getStatusRule() {
        return statusRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfiguration that = (GoalConfiguration) o;

        if (!bid.equals(that.bid)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!statusRule.equals(that.statusRule)) return false;
        if (!timeRule.equals(that.timeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid.hashCode();
        result = 31 * result + timeRule.hashCode();
        result = 31 * result + privacyRule.hashCode();
        result = 31 * result + statusRule.hashCode();
        return result;
    }

}
