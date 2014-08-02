package com.clemble.casino.goal;

import com.clemble.casino.payment.money.Money;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal that = (Goal) o;

        if (rate != that.rate) return false;
        if (!bet.equals(that.bet)) return false;
        if (!dueDate.equals(that.dueDate)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + bet.hashCode();
        result = 31 * result + rate;
        result = 31 * result + dueDate.hashCode();
        return result;
    }
}
