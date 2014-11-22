package com.clemble.casino.goal.lifecycle.initiation.event;

import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiationAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/13/14.
 */
@JsonTypeName("goal:initiation:created")
public class GoalInitiationCreatedEvent implements GoalInitiationEvent, GoalInitiationAware, PlayerAware {

    final public static String JSON_TYPE = "goal:initiation:created";

    final private String player;
    final private String goalKey;
    final private GoalInitiation initiation;

    @JsonCreator
    public GoalInitiationCreatedEvent(@JsonProperty(PLAYER) String player, @JsonProperty(GOAL_KEY) String goalKey, @JsonProperty(INITIATION) GoalInitiation initiation) {
        this.player = player;
        this.goalKey = goalKey;
        this.initiation = initiation;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public GoalInitiation getInitiation() {
        return initiation;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public static GoalInitiationCreatedEvent create(GoalInitiation initiation) {
        return new GoalInitiationCreatedEvent(initiation.getPlayer(), initiation.getGoalKey(), initiation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiationCreatedEvent that = (GoalInitiationCreatedEvent) o;

        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (initiation != null ? !initiation.equals(that.initiation) : that.initiation != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return goalKey != null ? goalKey.hashCode() : 0;
    }

    @Override
    public String toString() {
        return player + " > " + goalKey + " > " + JSON_TYPE;
    }

}
