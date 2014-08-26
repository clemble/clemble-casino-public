package com.clemble.casino.goal;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConstruction implements GoalAware, PlayerAware {

    final private String player;
    final private String goalKey;

    @JsonCreator
    public GoalConstruction(@JsonProperty(PLAYER) String player, @JsonProperty(GOAL_KEY) String goalKey) {
        this.player = player;
        this.goalKey = goalKey;
    }

    @Override
    public String getPlayer(){
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

        GoalConstruction that = (GoalConstruction) o;

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

}
