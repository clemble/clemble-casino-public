package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.lifecycle.management.PlayerContext;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalPlayerContext implements PlayerContext {

    final private String player;

    @JsonCreator
    public GoalPlayerContext(@JsonProperty(PLAYER) String player) {
        this.player = player;
    }

    public String getPlayer(){
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalPlayerContext that = (GoalPlayerContext) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }

}
