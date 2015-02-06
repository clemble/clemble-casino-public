package com.clemble.casino.goal.lifecycle.configuration.rule;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 1/6/15.
 */
public class GoalRuleValue<T extends ConfigurationRule> implements GoalRuleValueAware {

    final private T rule;
    final private int percentage;

    @JsonCreator
    public GoalRuleValue(@JsonProperty("rule") T rule, @JsonProperty("percentage") int percentage) {
        this.rule = rule;
        this.percentage = percentage;
    }

    @Override
    public int getPercentage() {
        return percentage;
    }

    @Override
    public T getRule() {
        return rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalRuleValue that = (GoalRuleValue) o;

        if (percentage != that.percentage) return false;
        if (!rule.equals(that.rule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rule.hashCode();
        result = 31 * result + percentage;
        return result;
    }

}
