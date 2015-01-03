package com.clemble.casino.goal.lifecycle.construction.event;

import com.clemble.casino.goal.lifecycle.construction.GoalSuggestion;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/3/15.
 */
@JsonTypeName(GoalSuggestionDeclinedEvent.JSON_TYPE)
public class GoalSuggestionDeclinedEvent implements GoalSuggestionEvent {

    final public static String JSON_TYPE = "goal:suggestion:declined";

    final private String player;
    final private GoalSuggestion body;

    @JsonCreator
    public GoalSuggestionDeclinedEvent(@JsonProperty(PLAYER) String player, @JsonProperty("body") GoalSuggestion body) {
        this.player = player;
        this.body = body;
    }

    @Override
    public GoalSuggestion getBody() {
        return body;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalSuggestionDeclinedEvent that = (GoalSuggestionDeclinedEvent) o;

        if (!body.equals(that.body)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

}
