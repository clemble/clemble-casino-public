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
@JsonTypeName(GoalStartedPost.JSON_TYPE)
public class GoalStartedPost implements GoalPost {

    final public static String JSON_TYPE = "post:goal:started";

    final private String key;
    final private String player;
    final private GoalState state;
    final private DateTime created;

    @JsonCreator
    public GoalStartedPost(
        @JsonProperty("key") String key,
        @JsonProperty("player") String player,
        @JsonProperty("bank") GoalState state,
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

    public static GoalStartedPost create(GoalState state) {
        return new GoalStartedPost(
            state.getGoalKey(),
            state.getPlayer(),
            state,
            DateTime.now(DateTimeZone.UTC)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalStartedPost)) return false;

        GoalStartedPost that = (GoalStartedPost) o;

        if (!created.equals(that.created)) return false;
        if (!key.equals(that.key)) return false;
        if (!player.equals(that.player)) return false;
        if (!state.equals(that.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }

}
