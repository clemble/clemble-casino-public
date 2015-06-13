package com.clemble.casino.goal.lifecycle.construction.event;

import com.clemble.casino.goal.lifecycle.construction.GoalConstruction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalConstructionCompletedEvent.JSON_TYPE)
public class GoalConstructionCompletedEvent implements GoalConstructionEvent {

    final public static String JSON_TYPE = "goal:construction:completed";

    final private String player;
    final private GoalConstruction construction;

    @JsonCreator
    public GoalConstructionCompletedEvent(@JsonProperty(PLAYER) String player, @JsonProperty("body") GoalConstruction construction) {
        this.player = player;
        this.construction = construction;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GoalConstruction getBody() {
        return construction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConstructionCompletedEvent that = (GoalConstructionCompletedEvent) o;

        if (!construction.equals(that.construction)) return false;
        return player.equals(that.player);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + construction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return player + " > " + construction.getGoalKey() + " > " + JSON_TYPE;
    }

}
