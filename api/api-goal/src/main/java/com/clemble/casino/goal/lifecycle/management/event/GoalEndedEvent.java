package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalMissedPost;
import com.clemble.casino.goal.post.GoalPost;
import com.clemble.casino.goal.post.GoalReachedPost;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeName(GoalEndedEvent.JSON_TYPE)
public class GoalEndedEvent implements GoalManagementEvent, OutcomeAware {

    final public static String JSON_TYPE = "goal:management:complete";

    final private String player;
    final private GoalState body;
    final private Outcome outcome;

    @JsonCreator
    public GoalEndedEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalState body,
        @JsonProperty("outcome") Outcome outcome) {
        this.player = player;
        this.body = body;
        this.outcome = outcome;
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
    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public GoalPost toPost() {
        if (outcome instanceof PlayerWonOutcome)
            return GoalReachedPost.create(body);
        else
            return GoalMissedPost.create(body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalEndedEvent that = (GoalEndedEvent) o;

        if (!player.equals(that.player)) return false;
        if (!body.equals(that.body)) return false;
        return outcome.equals(that.outcome);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + body.hashCode();
        result = 31 * result + outcome.hashCode();
        return result;
    }

}
