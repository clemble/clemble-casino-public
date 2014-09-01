package com.clemble.casino.goal.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mavarazy on 9/1/14.
 */
public class GoalConfigurations {

    final private List<GoalConfiguration> configurations;

    @JsonCreator
    public GoalConfigurations(@JsonProperty("configurations") List<GoalConfiguration> configurations) {
        this.configurations = configurations;
    }

    public List<GoalConfiguration> getConfigurations() {
        return configurations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConfigurations that = (GoalConfigurations) o;

        if (configurations != null ? !configurations.equals(that.configurations) : that.configurations != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return configurations != null ? configurations.hashCode() : 0;
    }

}
