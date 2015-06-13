package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
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
 * Created by mavarazy on 1/17/15.
 */
@JsonTypeName(GoalBetPost.JSON_TYPE)
public class GoalBetOffPost implements GoalPost {

    final public static String JSON_TYPE = "post:goal:bet:off";

    final private String key;
    final private String player;
    final private GoalState state;
    final private DateTime created;

    @JsonCreator
    public GoalBetOffPost(
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

    public static GoalBetOffPost create(GoalState state) {
        return new GoalBetOffPost(
            state.getGoalKey(),
            state.getPlayer(),
            state,
            DateTime.now(DateTimeZone.UTC)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalBetOffPost)) return false;

        GoalBetOffPost that = (GoalBetOffPost) o;

        if (!created.equals(that.created)) return false;
        if (!key.equals(that.key)) return false;
        if (!player.equals(that.player)) return false;
        return state.equals(that.state);

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
