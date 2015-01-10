package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 1/6/15.
 */
public class GoalConfigurationValue<T extends ConfigurationRule> {

    final private T rule;
    final private int percentage;

    @JsonCreator
    public GoalConfigurationValue(@JsonProperty("rule") T rule, @JsonProperty("percentage") int percentage) {
        this.rule = rule;
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    public T getRule() {
        return rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfigurationValue that = (GoalConfigurationValue) o;

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
