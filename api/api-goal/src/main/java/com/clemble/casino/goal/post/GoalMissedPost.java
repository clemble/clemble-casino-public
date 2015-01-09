package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;
import java.util.Set;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalMissedPost.JSON_TYPE)
public class GoalMissedPost implements GoalPost {

    final public static String JSON_TYPE = "post:goal:missed";

    final private String key;
    final private String player;
    final private Bank bank;
    final private String goal;
    final private String status;
    final private String goalKey;
    final private Set<String> observers;
    final private Set<String> supporters;
    final private long deadline;
    final private Date created;


    @JsonCreator
    public GoalMissedPost(
        @JsonProperty("key") String key,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("status") String status,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("observers") Set<String> observers,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("created") Date created
    ) {
        this.key = key;
        this.goalKey = goalKey;
        this.player = player;
        this.goal = goal;
        this.bank = bank;
        this.status = status;
        this.deadline = deadline;
        this.observers = observers;
        this.supporters = supporters;
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
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public Set<String> getObservers() {
        return observers;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public long getDeadline() {
        return deadline;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    public static GoalMissedPost create(GoalState state) {
        return new GoalMissedPost(
            state.getGoalKey(),
            state.getGoalKey(),
            state.getPlayer(),
            state.getBank(),
            state.getGoal(),
            state.getStatus(),
            state.getContext().getPlayerContext(state.getPlayer()).getClock().getDeadline(),
            state.getObservers(),
            state.getSupporters(),
            new Date()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalMissedPost that = (GoalMissedPost) o;

        if (deadline != that.deadline) return false;
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
        result = 31 * result + status.hashCode();
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        return result;
    }


}
