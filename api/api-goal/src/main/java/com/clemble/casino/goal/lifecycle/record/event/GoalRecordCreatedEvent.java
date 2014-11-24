package com.clemble.casino.goal.lifecycle.record.event;

import com.clemble.casino.goal.lifecycle.record.GoalRecord;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/24/14.
 */
@JsonTypeName(GoalRecordCreatedEvent.JSON_TYPE)
public class GoalRecordCreatedEvent implements GoalRecordEvent {

    final public static String JSON_TYPE = "goal:record:created";

    final private String player;
    final private GoalRecord record;

    @JsonCreator
    public GoalRecordCreatedEvent(@JsonProperty(PLAYER) String player, @JsonProperty("body") GoalRecord record) {
        this.player = player;
        this.record = record;
    }

    @Override
    public GoalRecord getBody() {
        return record;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalRecordCreatedEvent that = (GoalRecordCreatedEvent) o;

        if (!player.equals(that.player)) return false;
        if (!record.equals(that.record)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + record.hashCode();
        return result;
    }

    public static GoalRecordCreatedEvent create(GoalRecord record) {
        return new GoalRecordCreatedEvent(record.getPlayer(), record);
    }

}
