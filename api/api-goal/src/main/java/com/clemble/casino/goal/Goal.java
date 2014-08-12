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

import java.util.Date;

/**
 * Created by mavarazy on 8/2/14.
 */
public class Goal implements GoalAware, PlayerAware {

    @Id
    final private GoalKey goalKey;
    final private String player;
    final private String description;
    final private Date dueDate;
    final private GoalState state;
    final private Bid bid;

    @JsonCreator
    public Goal(
            @JsonProperty("goalKey") GoalKey goalKey,
            @JsonProperty("player") String player,
            @JsonProperty("description") String description,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("state") GoalState state,
            @JsonProperty("bid") Bid bid) {
        this.goalKey = goalKey;
        this.player = player;
        this.dueDate = dueDate;
        this.description = description;
        this.state = state;
        this.bid = bid;
    }

    @Override
    public GoalKey getGoalKey() {
        return goalKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Bid getBid() {
        return bid;
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

    public Goal cloneWithPlayerAndGoal(String player, String goal, GoalState state) {
        return new Goal(new GoalKey(player, goal), player, description, dueDate, state, bid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (!bid.equals(goal.bid)) return false;
        if (!description.equals(goal.description)) return false;
        if (!dueDate.equals(goal.dueDate)) return false;
        if (!goalKey.equals(goal.goalKey)) return false;
        if (state != goal.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + dueDate.hashCode();
        result = 31 * result + bid.hashCode();
        return result;
    }
}
