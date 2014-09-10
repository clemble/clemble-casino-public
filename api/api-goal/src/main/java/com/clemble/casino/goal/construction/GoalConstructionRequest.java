package com.clemble.casino.goal.construction;

import com.clemble.casino.construction.ConstructionState;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.configuration.GoalConfigurationAware;
import com.clemble.casino.construction.ConstructionRequest;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.spi.ConfigurationState;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConstructionRequest implements GoalConfigurationAware, ConstructionRequest<GoalConfiguration>, GoalDescriptionAware, PlayerAware {

    final private String player;
    final private String goal;
    final private String judge;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalConstructionRequest(
        @JsonProperty("player") String player,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("goal") String goal,
        @JsonProperty("judge") String judge) {
        this.player = player;
        this.configuration = configuration;
        this.goal = goal;
        this.judge = judge;
    }

    @Override
    public String getPlayer() {
        return player;
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

    public GoalConstruction toConstruction(String goalKey) {
        return new GoalConstruction(goalKey, player, judge, goal, configuration, ConstructionState.pending);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConstructionRequest that = (GoalConstructionRequest) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (goal != null ? !goal.equals(that.goal) : that.goal != null) return false;
        if (judge != null ? !judge.equals(that.judge) : that.judge != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (judge != null ? judge.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }

}
