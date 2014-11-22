package com.clemble.casino.goal.lifecycle.initiation.event;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/16/14.
 */
@JsonTypeName(GoalInitiationCompleteEvent.JSON_TYPE)
public class GoalInitiationCompleteEvent implements GoalInitiationEvent, PlayerAware {

    final public static String JSON_TYPE = "goal:initiation:complete";

    final private String player;
    final private String goalKey;

    @JsonCreator
    public GoalInitiationCompleteEvent(@JsonProperty(PLAYER) String player, @JsonProperty("goalKey") String goalKey) {
        this.player = player;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiationCompleteEvent that = (GoalInitiationCompleteEvent) o;

        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goalKey.hashCode();
    }

    @Override
    public String toString() {
        return player + " > " + goalKey + " > " + JSON_TYPE;
    }

}
