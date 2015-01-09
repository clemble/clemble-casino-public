package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;
import java.util.Set;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalCreatedPost.JSON_TYPE)
public class GoalCreatedPost implements GoalPost, GoalConfigurationAware {

    final public static String JSON_TYPE = "post:goal:created";

    final private String player;
    final private Bank bank;
    final private String goal;
    final private String goalKey;
    final private Date startDate;
    final private long deadline;
    final private Set<String> observers;
    final private Set<String> supporters;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalCreatedPost(
        @JsonProperty("key") String key,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("goal") String goal,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("observers") Set<String> observers,
        @JsonProperty("supporters") Set<String> supporters) {
        this.goalKey = goalKey;
        this.player = player;
        this.startDate = startDate;
        this.goal = goal;
        this.bank = bank;
        this.deadline = deadline;
        this.configuration = configuration;
        this.observers = observers;
        this.supporters = supporters;
    }

    @Override
    public String getKey() {
        return goalKey;
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

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalCreatedPost that = (GoalCreatedPost) o;

        if (deadline != that.deadline) return false;
        if (!bank.equals(that.bank)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;
        if (!startDate.equals(that.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + goalKey.hashCode();
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        return result;
    }

    public static GoalCreatedPost create(GoalInitiation initiation) {
        return new GoalCreatedPost(
            initiation.getGoalKey(),
            initiation.getGoalKey(),
            initiation.getPlayer(),
            initiation.getBank(),
            initiation.getConfiguration(),
            initiation.getGoal(),
            initiation.getStartDate(),
            initiation.getConfiguration().getTotalTimeoutRule().getTimeoutCalculator().calculate(initiation.getStartDate().getTime()),
            initiation.getObservers(),
            initiation.getSupporters()
        );
    }

}
