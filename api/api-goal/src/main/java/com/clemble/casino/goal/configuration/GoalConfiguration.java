package com.clemble.casino.goal.configuration;

import com.clemble.casino.money.Money;
import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.clemble.casino.rule.time.MoveTimeRule;
import com.clemble.casino.rule.time.TotalTimeRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 8/26/14.
 */
@JsonTypeName("goal")
public class GoalConfiguration implements Configuration {

    final private Money price;
    final private MoveTimeRule moveTimeRule;
    final private TotalTimeRule totalTimeRule;
    final private PrivacyRule privacyRule;

    @JsonCreator
    public GoalConfiguration(
        @JsonProperty("price") Money price,
        @JsonProperty("moveTimeRule") MoveTimeRule moveTimeRule,
        @JsonProperty("totalTimeRule") TotalTimeRule totalTimeRule,
        @JsonProperty("privacyRule") PrivacyRule privacyRule) {
        this.price = price;
        this.moveTimeRule = moveTimeRule;
        this.totalTimeRule = totalTimeRule;
        this.privacyRule = privacyRule;
    }

    public Money getPrice() {
        return price;
    }

    public MoveTimeRule getMoveTimeRule() {
        return moveTimeRule;
    }

    public TotalTimeRule getTotalTimeRule() {
        return totalTimeRule;
    }

    @Override
    public PrivacyRule getPrivacyRule() {
        return privacyRule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfiguration that = (GoalConfiguration) o;

        if (!price.equals(that.price)) return false;
        if (privacyRule != that.privacyRule) return false;
        if (!moveTimeRule.equals(that.moveTimeRule)) return false;
        if (!totalTimeRule.equals(that.totalTimeRule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = price.hashCode();
        result = 31 * result + moveTimeRule.hashCode();
        result = 31 * result + totalTimeRule.hashCode();
        result = 31 * result + privacyRule.hashCode();
        return result;
    }

}
