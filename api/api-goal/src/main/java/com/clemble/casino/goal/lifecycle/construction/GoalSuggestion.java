package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.lifecycle.construction.Construction;
import com.clemble.casino.lifecycle.initiation.InitiationState;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mavarazy on 1/3/15.
 */
public class GoalSuggestion implements GoalDescriptionAware, PlayerAware, GoalAware, GoalConfigurationAware {

    @Id
    final private String goalKey;
    final private String goal;
    final private String player;
    final private String suggester;
    final private GoalConfiguration configuration;
    final private GoalSuggestionState state;

    @JsonCreator
    public GoalSuggestion(
        @JsonProperty(GOAL_KEY) String goalKey,
        @JsonProperty("goal") String goal,
        @JsonProperty("player") String player,
        @JsonProperty("suggester") String suggester,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("state") GoalSuggestionState state) {
        this.goalKey = goalKey;
        this.player = player;
        this.suggester = suggester;
        this.goal = goal;
        this.configuration = configuration;
        this.state = state;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getSuggester() {
        return suggester;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    public GoalSuggestionState getState() {
        return  state;
    }

    public GoalInitiation toInitiation() {
        return new GoalInitiation(
            goalKey,
            InitiationState.pending,
            Bank.create(getPlayer(), getConfiguration().getBid()),
            player,
            goal,
            configuration,
            new HashSet<String>(),
            new HashSet<String>(),
            new Date());
    }

    public GoalSuggestion copyWithStatus(GoalSuggestionState state) {
        return new GoalSuggestion(goalKey, goal, player, suggester, configuration, state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalSuggestion that = (GoalSuggestion) o;

        if (!configuration.equals(that.configuration)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!suggester.equals(that.suggester)) return false;
        if (!player.equals(that.player)) return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + configuration.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + suggester.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }
}
