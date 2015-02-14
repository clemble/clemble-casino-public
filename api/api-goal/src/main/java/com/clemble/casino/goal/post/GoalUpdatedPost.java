package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;
import java.util.Set;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalUpdatedPost.JSON_TYPE)
public class GoalUpdatedPost implements GoalPost {

    final public static String JSON_TYPE = "post:goal:updated";

    final private String key;
    final private String player;
    final private Bank bank;
    final private GoalConfiguration configuration;
    final private String goal;
    final private String reward;
    final private String status;
    final private String goalKey;
    final private Set<String> supporters;
    final private DateTime deadline;
    final private DateTime created;
    final private boolean betsAllowed;

    @JsonCreator
    public GoalUpdatedPost(
        @JsonProperty("key") String key,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("goal") String goal,
        @JsonProperty("reward") String reward,
        @JsonProperty("status") String status,
        @JsonProperty("deadline") DateTime deadline,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("created") DateTime created,
        @JsonProperty("betsAllowed") boolean betsAllowed
    ) {
        this.key = key;
        this.goalKey = goalKey;
        this.player = player;
        this.goal = goal;
        this.reward = reward;
        this.bank = bank;
        this.configuration = configuration;
        this.status = status;
        this.deadline = deadline;
        this.supporters = supporters;
        this.created = created;
        this.betsAllowed = betsAllowed;
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
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public String getReward() {
        return reward;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean getBetsAllowed() {
        return betsAllowed;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public DateTime getDeadline() {
        return deadline;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    public static GoalUpdatedPost create(GoalState state) {
        return new GoalUpdatedPost(
            state.getGoalKey(),
            state.getGoalKey(),
            state.getPlayer(),
            state.getBank(),
            state.getConfiguration(),
            state.getGoal(),
            state.getReward(),
            state.getStatus(),
            state.getDeadline(),
            state.getSupporters(),
            DateTime.now(),
            state.getBetsAllowed()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalUpdatedPost that = (GoalUpdatedPost) o;

        if (!deadline.equals(that.deadline)) return false;
        if (!bank.equals(that.bank)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;
        if (!status.equals(that.status)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + goalKey.hashCode();
        result = 31 * result + status != null ? status.hashCode() : 0;
        result = 31 * result + deadline.hashCode();
        return result;
    }

}
