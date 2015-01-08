package com.clemble.casino.goal.lifecycle.configuration;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;

import java.util.List;

/**
 * Created by mavarazy on 1/6/15.
 */
public class GoalConfigurationOption<T extends ConfigurationRule> {

    final private List<GoalConfigurationValue<T>> values;

    public GoalConfigurationOption(List<GoalConfigurationValue<T>> values) {
        this.values = values;
    }

    public List<GoalConfigurationValue<T>> getValues() {
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfigurationOption that = (GoalConfigurationOption) o;

        if (!values.equals(that.values)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

}
