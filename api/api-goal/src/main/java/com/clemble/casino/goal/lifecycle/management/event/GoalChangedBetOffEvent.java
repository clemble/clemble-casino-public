package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalBetOffPost;
import com.clemble.casino.goal.post.GoalPost;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/17/15.
 */
@JsonTypeName(GoalChangedBetOffEvent.JSON_TYPE)
public class GoalChangedBetOffEvent implements GoalManagementEvent {

    final public static String JSON_TYPE = "goal:management:changed:bet:off";

    final private String player;
    final private GoalState body;

    @JsonCreator
    public GoalChangedBetOffEvent(
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
        return GoalBetOffPost.create(body);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalChangedBetOffEvent)) return false;

        GoalChangedBetOffEvent that = (GoalChangedBetOffEvent) o;

        if (!body.equals(that.body)) return false;
        return player.equals(that.player);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

}
