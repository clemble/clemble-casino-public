package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalPost;
import com.clemble.casino.goal.post.GoalUpdatedPost;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.post.PlayerPost;
import com.clemble.casino.post.PlayerPostConvertible;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalChangedEvent.JSON_TYPE)
public class GoalChangedEvent implements GoalManagementEvent {

    final public static String JSON_TYPE = "goal:management:changed";

    final private String player;
    final private GoalState state;

    @JsonCreator
    public GoalChangedEvent(
        @JsonProperty("player") String player,
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
    public GoalPost toPost() {
        return GoalUpdatedPost.create(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalChangedEvent that = (GoalChangedEvent) o;

        if (!player.equals(that.player)) return false;
        if (!state.equals(that.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

}
