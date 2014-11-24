package com.clemble.casino.goal.lifecycle.initiation.event;

import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/24/14.
 */
@JsonTypeName(GoalInitiationChangedEvent.JSON_TYPE)
public class GoalInitiationChangedEvent implements GoalInitiationEvent, GoalInitiationAware {
    final public static String JSON_TYPE = "goal:initiation:changed";

    final private String goalKey;
    final private String player;
    final private GoalInitiation initiation;

    @JsonCreator
    public GoalInitiationChangedEvent(@JsonProperty(GOAL_KEY) String goalKey, @JsonProperty(PLAYER) String player, @JsonProperty(INITIATION) GoalInitiation initiation) {
        this.goalKey = goalKey;
        this.player = player;
        this.initiation = initiation;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public GoalInitiation getInitiation() {
        return initiation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiationChangedEvent that = (GoalInitiationChangedEvent) o;

        if (!goalKey.equals(that.goalKey)) return false;
        if (!initiation.equals(that.initiation)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + initiation.hashCode();
        return result;
    }

    public static GoalInitiationChangedEvent create(GoalInitiation initiation) {
        return new GoalInitiationChangedEvent(initiation.getGoalKey(), initiation.getPlayer(), initiation);
    }

}
