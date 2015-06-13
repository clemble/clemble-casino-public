package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalPost;
import com.clemble.casino.goal.post.GoalStartedPost;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalStartedEvent.JSON_TYPE)
public class GoalStartedEvent implements GoalManagementEvent, PlayerAware {

    final public static String JSON_TYPE = "goal:management:created";

    final private String player;
    final private GoalState body;

    @JsonCreator
    public GoalStartedEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalState body) {
        this.player = player;
        this.body = body;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GoalState getBody() {
        return body;
    }

    @Override
    public GoalPost toPost() {
        return GoalStartedPost.create(body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStartedEvent that = (GoalStartedEvent) o;

        if (!body.equals(that.body)) return false;
        return player.equals(that.player);

    }

    @Override
    public int hashCode() {
        int result = body.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return player + " > " + body.getGoalKey() + " > " + JSON_TYPE;
    }

}
