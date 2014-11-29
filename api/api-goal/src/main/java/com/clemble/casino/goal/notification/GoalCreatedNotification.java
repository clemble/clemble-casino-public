package com.clemble.casino.goal.notification;

import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.lifecycle.initiation.event.GoalInitiationCreatedEvent;
import com.clemble.casino.lifecycle.configuration.rule.time.DeadlineAware;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.payment.BankAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalCreatedNotification.JSON_TYPE)
public class GoalCreatedNotification implements GoalNotification {

    final public static String JSON_TYPE = "notification:goal:created";

    final private String player;
    final private Bank bank;
    final private String goal;
    final private String goalKey;
    final private long deadline;

    @JsonCreator
    public GoalCreatedNotification(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("deadline") long deadline) {
        this.goalKey = goalKey;
        this.player = player;
        this.goal = goal;
        this.bank = bank;
        this.deadline = deadline;
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

        GoalCreatedNotification that = (GoalCreatedNotification) o;

        if (deadline != that.deadline) return false;
        if (!bank.equals(that.bank)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;

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

    public static GoalCreatedNotification create(GoalInitiation initiation) {
        return new GoalCreatedNotification(
            initiation.getGoalKey(),
            initiation.getPlayer(),
            initiation.getBank(),
            initiation.getGoal(),
            initiation.getStartDate().getTime() + initiation.getConfiguration().getTotalTimeRule().getLimit()
        );
    }

}
