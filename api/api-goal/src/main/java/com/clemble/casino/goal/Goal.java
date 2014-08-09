package com.clemble.casino.goal;

import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by mavarazy on 8/2/14.
 */
public class Goal implements GoalAware {

    final private String player;
    final private String goal;
    final private String description;
    final private GoalState state;
    final private Money bet;
    final private int rate;
    final private Date dueDate;

    @JsonCreator
    public Goal(
            @JsonProperty("player") String player,
            @JsonProperty("goal") String goal,
            @JsonProperty("description") String description,
            @JsonProperty("bet") Money bet,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("rate") int rate,
            @JsonProperty("state") GoalState state) {
        this.player = player;
        this.dueDate = dueDate;
        this.description = description;
        this.state = state;
        this.rate = rate;
        this.goal = goal;
        this.bet = bet;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    public String getDescription() {
        return description;
    }

    public GoalState getState() {
        return state;
    }

    public Money getBet() {
        return bet;
    }

    public int getRate() {
        return rate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Goal cloneWithPlayerAndGoal(String player, String goal) {
        return new Goal(player, goal, description, bet, dueDate, rate, state);
    }

    public Goal cloneWithGoal(String goal) {
        return new Goal(player, goal, description, bet, dueDate, rate, state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal1 = (Goal) o;

        if (rate != goal1.rate) return false;
        if (bet != null ? !bet.equals(goal1.bet) : goal1.bet != null) return false;
        if (description != null ? !description.equals(goal1.description) : goal1.description != null) return false;
        if (dueDate != null ? !dueDate.equals(goal1.dueDate) : goal1.dueDate != null) return false;
        if (goal != null ? !goal.equals(goal1.goal) : goal1.goal != null) return false;
        if (player != null ? !player.equals(goal1.player) : goal1.player != null) return false;
        if (state != goal1.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (bet != null ? bet.hashCode() : 0);
        result = 31 * result + rate;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        return result;
    }
}
