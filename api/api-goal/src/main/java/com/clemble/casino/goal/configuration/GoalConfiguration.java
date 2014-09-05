package com.clemble.casino.goal.configuration;

import com.clemble.casino.goal.rule.status.GoalStatusRule;
import com.clemble.casino.goal.rule.time.GoalTimeRule;
import com.clemble.casino.money.Money;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConfiguration {

    final private Money price;
    final private GoalTimeRule timeRule;
    final private PrivacyRule privacyRule;
    final private GoalStatusRule statusRule;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("price") Money price,
        @JsonProperty("timeRule") GoalTimeRule timeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule,
        @JsonProperty("statusRule") GoalStatusRule statusRule) {
        this.price = price;
        this.timeRule = timeRule;
        this.privacyRule = privacyRule;
        this.statusRule = statusRule;
    }

    public Money getPrice() {
        return price;
    }

    public GoalTimeRule getTimeRule() {
        return timeRule;
    }

    public PrivacyRule getPrivacyRule() {
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

        if (!price.equals(that.price)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!statusRule.equals(that.statusRule)) return false;
        if (!timeRule.equals(that.timeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + timeRule.hashCode();
        result = 31 * result + privacyRule.hashCode();
        result = 31 * result + statusRule.hashCode();
        return result;
    }

}
