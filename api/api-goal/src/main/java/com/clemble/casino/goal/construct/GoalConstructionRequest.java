package com.clemble.casino.goal.construct;

import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.configuration.GoalConfigurationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConstructionRequest implements GoalConfigurationAware {

    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalConstructionRequest(@JsonProperty("configuration") GoalConfiguration configuration) {
        this.configuration = configuration;
    }

    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConstructionRequest that = (GoalConstructionRequest) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return configuration != null ? configuration.hashCode() : 0;
    }
}
