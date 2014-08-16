package com.clemble.casino.goal;

import com.clemble.casino.bet.BetAware;
import com.clemble.casino.bet.Bid;
import com.clemble.casino.payment.PaymentTransactionAware;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

/**
 * Created by mavarazy on 8/2/14.
 */
public class Goal implements GoalAware, PlayerAware {

    @Id
    final private GoalKey goalKey;
    final private String player;
    final private String description;
    final private Date startDate;
    final private Date dueDate;
    final private GoalStatus status;
    final private GoalState state;

    @JsonCreator
    public Goal(
            @JsonProperty("goalKey") GoalKey goalKey,
            @JsonProperty("player") String player,
            @JsonProperty("description") String description,
            @JsonProperty("startDate") Date startDate,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("state") GoalState state,
            @JsonProperty("status") GoalStatus status) {
        this.goalKey = goalKey;
        this.player = player;
        this.state = state;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.description = description;
        this.status = status;
    }

    @Override
    public GoalKey getGoalKey() {
        return goalKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getDescription() {
        return description;
    }

    public GoalState getState() {
        return state;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public Goal cloneWithStatus(GoalStatus status) {
        return new Goal(goalKey, player, description, startDate, dueDate, state, status);
    }

    public Goal cloneWithPlayerAndGoal(String player, String goal, GoalState state) {
        return new Goal(new GoalKey(player, goal), player, description, startDate, dueDate, state, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (description != null ? !description.equals(goal.description) : goal.description != null) return false;
        if (dueDate != null ? !dueDate.equals(goal.dueDate) : goal.dueDate != null) return false;
        if (goalKey != null ? !goalKey.equals(goal.goalKey) : goal.goalKey != null) return false;
        if (player != null ? !player.equals(goal.player) : goal.player != null) return false;
        if (startDate != null ? !startDate.equals(goal.startDate) : goal.startDate != null) return false;
        if (state != goal.state) return false;
        if (status != null ? !status.equals(goal.status) : goal.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
