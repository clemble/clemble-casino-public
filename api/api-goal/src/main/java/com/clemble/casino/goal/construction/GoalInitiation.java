package com.clemble.casino.goal.construction;

import com.clemble.casino.construction.Initiation;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 9/12/14.
 */
public class GoalInitiation implements GoalAware, PlayerAware, Initiation<GoalConfiguration> {

    @Id
    final private String goalKey;
    final private String player;
    final private Date startDate;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalInitiation(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("startDate") Date startDate) {
        this.player = player;
        this.configuration = configuration;
        this.startDate = startDate;
        this.goalKey = goalKey;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Date getStartDate() {
        return startDate;
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
        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }
}
