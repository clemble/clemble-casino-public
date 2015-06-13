package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalPost;
import com.clemble.casino.goal.post.GoalUpdatedPost;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalChangedStatusEvent.JSON_TYPE)
public class GoalChangedStatusEvent implements GoalManagementEvent {

    final public static String JSON_TYPE = "goal:management:changed:status";

    final private String player;
    final private GoalState body;

    @JsonCreator
    public GoalChangedStatusEvent(
        @JsonProperty("player") String player,
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
        return GoalUpdatedPost.create(body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalChangedStatusEvent that = (GoalChangedStatusEvent) o;

        if (!player.equals(that.player)) return false;
        return body.equals(that.body);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

}
