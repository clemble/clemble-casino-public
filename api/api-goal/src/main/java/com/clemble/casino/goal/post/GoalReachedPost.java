package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.management.GoalPhase;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Set;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalReachedPost.JSON_TYPE)
public class GoalReachedPost implements GoalPost {
    final public static String JSON_TYPE = "post:goal:reached";

    final private String key;
    final private String player;
    final private GoalState state;
    final private DateTime created;

    @JsonCreator
    public GoalReachedPost(
        @JsonProperty("key") String key,
        @JsonProperty("player") String player,
        @JsonProperty("state") GoalState state,
        @JsonProperty("created") DateTime created
    ) {
        this.key = key;
        this.player = player;
        this.state = state;
        this.created = created;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GoalState getState() {
        return state;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    public static GoalReachedPost create(GoalState state) {
        return new GoalReachedPost(
            state.getGoalKey(),
            state.getPlayer(),
            state,
            DateTime.now(DateTimeZone.UTC)
        );
    }

}
