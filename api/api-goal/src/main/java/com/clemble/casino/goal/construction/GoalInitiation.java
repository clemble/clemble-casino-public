package com.clemble.casino.goal.construction;

import com.clemble.casino.construction.Initiation;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 9/12/14.
 */
public class GoalInitiation implements Initiation<GoalConfiguration> {

    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalInitiation(@JsonProperty("configuration") GoalConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiation that = (GoalInitiation) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return configuration != null ? configuration.hashCode() : 0;
    }

}
