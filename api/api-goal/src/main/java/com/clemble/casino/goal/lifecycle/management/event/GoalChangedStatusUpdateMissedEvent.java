package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalPost;
import com.clemble.casino.goal.post.GoalStatusMissedPost;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/17/15.
 */
@JsonTypeName(GoalChangedStatusUpdateMissedEvent.JSON_TYPE)
public class GoalChangedStatusUpdateMissedEvent implements GoalManagementEvent{

    final public static String JSON_TYPE = "goal:management:changed:status:missed";

    final private String player;
    final private GoalState body;

    @JsonCreator
    public GoalChangedStatusUpdateMissedEvent(
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
        return GoalStatusMissedPost.create(body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalChangedStatusUpdateMissedEvent that = (GoalChangedStatusUpdateMissedEvent) o;

        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }
}
