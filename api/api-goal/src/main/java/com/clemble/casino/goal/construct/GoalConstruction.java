package com.clemble.casino.goal.construct;

import com.clemble.casino.construct.Construction;
import com.clemble.casino.construct.ConstructionState;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 9/7/14.
 */
public class GoalConstruction implements Construction<GoalConfiguration> {

    final private ConstructionState state;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalConstruction(@JsonProperty("configuration") GoalConfiguration configuration, @JsonProperty("state") ConstructionState state) {
        this.configuration = configuration;
        this.state = state;
    }

    @Override
    public ConstructionState getState() {
        return state;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConstruction that = (GoalConstruction) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }
}
