package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 1/3/15.
 */
public class GoalSuggestionRequest implements GoalConfigurationAware, GoalDescriptionAware {

    final private String goal;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalSuggestionRequest(@JsonProperty("goal") String goal, @JsonProperty("configuration") GoalConfiguration configuration) {
        this.goal = goal;
        this.configuration = configuration;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalSuggestionRequest that = (GoalSuggestionRequest) o;

        if (!configuration.equals(that.configuration)) return false;
        if (!goal.equals(that.goal)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goal.hashCode();
        result = 31 * result + configuration.hashCode();
        return result;
    }
}
