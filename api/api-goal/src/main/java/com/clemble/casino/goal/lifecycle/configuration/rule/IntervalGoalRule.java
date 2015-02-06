package com.clemble.casino.goal.lifecycle.configuration.rule;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 2/6/15.
 */
public class IntervalGoalRule<T extends ConfigurationRule> implements GoalRuleValueAware<T> {

    final private T rule;
    final private int interval;
    final private int percentage;

    @JsonCreator
    public IntervalGoalRule(
        @JsonProperty("rule") T rule,
        @JsonProperty("interval") int interval,
        @JsonProperty("percentage") int percentage) {
        this.rule = rule;
        this.interval = interval;
        this.percentage = percentage;
    }

    @Override
    public T getRule() {
        return rule;
    }

    @Override
    public int getPercentage() {
        return percentage;
    }

    public int getInterval() {
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntervalGoalRule)) return false;

        IntervalGoalRule that = (IntervalGoalRule) o;

        if (interval != that.interval) return false;
        if (percentage != that.percentage) return false;
        if (!rule.equals(that.rule)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rule.hashCode();
        result = 31 * result + interval;
        result = 31 * result + percentage;
        return result;
    }

}
