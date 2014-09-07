package com.clemble.casino.goal.construct;

import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.configuration.GoalConfigurationAware;
import com.clemble.casino.construct.ConstructionRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConstructionRequest implements GoalConfigurationAware, ConstructionRequest<GoalConfiguration>, GoalDescriptionAware {

    final private String goal;
    final private String judge;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalConstructionRequest(@JsonProperty("configuration") GoalConfiguration configuration, @JsonProperty("goal") String goal, @JsonProperty("judge") String judge) {
        this.configuration = configuration;
        this.goal = goal;
        this.judge = judge;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public String getJudge() {
        return judge;
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
