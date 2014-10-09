package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.lifecycle.management.StateContext;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalContext implements StateContext<GoalPlayerContext> {

    final private GoalContext parent;
    final private List<GoalPlayerContext> playerContexts;

    @JsonCreator
    public GoalContext(@JsonProperty("parent") GoalContext parent, @JsonProperty("playerContexts") List<GoalPlayerContext> playerContexts) {
        this.parent = parent;
        this.playerContexts = playerContexts;
    }

    @Override
    public GoalContext getParent() {
        return parent;
    }

    @Override
    public List<GoalPlayerContext> getPlayerContexts() {
        return playerContexts;
    }

    @Override
    public GoalPlayerContext getPlayerContext(String player) {
        return PlayerAwareUtils.filter(player, playerContexts);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalContext that = (GoalContext) o;

        if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;
        if (playerContexts != null ? !playerContexts.equals(that.playerContexts) : that.playerContexts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parent != null ? parent.hashCode() : 0;
        result = 31 * result + (playerContexts != null ? playerContexts.hashCode() : 0);
        return result;
    }
}
