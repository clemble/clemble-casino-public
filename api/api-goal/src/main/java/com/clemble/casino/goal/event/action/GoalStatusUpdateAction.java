package com.clemble.casino.goal.event.action;

import com.clemble.casino.goal.GoalStatusAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalStatusUpdateAction.JSON_TYPE)
public class GoalStatusUpdateAction implements PlayerGoalAction, GoalStatusAware {

    final public static String JSON_TYPE = "goal:management:status:update:action";

    final private String player;
    final private String goalKey;
    final private String status;
    final private int progress;

    @JsonCreator
    public GoalStatusUpdateAction(
        @JsonProperty("player") String player,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("status") String status,
        @JsonProperty("progress") int progress) {
        this.goalKey = goalKey;
        this.player = player;
        this.status = status;
        this.progress = progress;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public int getProgress() {
        return progress;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStatusUpdateAction that = (GoalStatusUpdateAction) o;

        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (goalKey != null ? goalKey.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return goalKey + ":" + player + " > " + JSON_TYPE + " > " + status;
    }

}
