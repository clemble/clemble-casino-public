package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalStartedPost;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.post.PlayerPost;
import com.clemble.casino.post.PlayerPostConvertible;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalStartedEvent.JSON_TYPE)
public class GoalStartedEvent implements GoalManagementEvent, PlayerAware, PlayerPostConvertible {

    final public static String JSON_TYPE = "goal:management:created";

    final private String player;
    final private GoalState state;

    @JsonCreator
    public GoalStartedEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalState state) {
        this.player = player;
        this.state = state;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GoalState getBody() {
        return state;
    }

    @Override
    public PlayerPost toPost() {
        return GoalStartedPost.create(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStartedEvent that = (GoalStartedEvent) o;

        if (!state.equals(that.state)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return player + " > " + state.getGoalKey() + " > " + JSON_TYPE;
    }

}
