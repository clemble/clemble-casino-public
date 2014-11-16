package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.GoalStatusAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalChangedEvent.JSON_TYPE)
public class GoalChangedEvent implements GoalManagementEvent, GoalStatusAware, PlayerAware {

    final public static String JSON_TYPE = "goal:management:status:update";

    final private String goalKey;
    final private String player;
    final private String status;

    @JsonCreator
    public GoalChangedEvent(
            @JsonProperty("goalKey") String goalKey,
            @JsonProperty("player") String player,
            @JsonProperty("status") String status) {
        this.goalKey = goalKey;
        this.player = player;
        this.status = status;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalChangedEvent that = (GoalChangedEvent) o;

        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return goalKey + ':' + player + " > " + JSON_TYPE + " > " + status;
    }

}
