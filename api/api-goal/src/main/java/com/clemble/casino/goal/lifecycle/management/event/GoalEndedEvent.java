package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.notification.GoalMissedNotification;
import com.clemble.casino.goal.notification.GoalReachedNotification;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalEndedEvent.JSON_TYPE)
public class GoalEndedEvent implements GoalManagementEvent, OutcomeAware, PlayerNotificationConvertible {

    final public static String JSON_TYPE = "goal:management:complete";

    final private String player;
    final private GoalState state;
    final private Outcome outcome;

    @JsonCreator
    public GoalEndedEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalState state,
        @JsonProperty("outcome") Outcome outcome) {
        this.player = player;
        this.state = state;
        this.outcome = outcome;
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
    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public PlayerNotification toNotification() {
        if (outcome instanceof PlayerWonOutcome)
            return GoalReachedNotification.create(state);
        else
            return GoalMissedNotification.create(state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalEndedEvent that = (GoalEndedEvent) o;

        if (!player.equals(that.player)) return false;
        if (!state.equals(that.state)) return false;
        if (!outcome.equals(that.outcome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + outcome.hashCode();
        return result;
    }

}
