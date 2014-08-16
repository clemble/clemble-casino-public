package com.clemble.casino.goal;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/16/14.
 */
public class GoalRequest implements PlayerAware, AmountAware {

    final private String player;
    final private String goal;
    final private int time;
    final private Money amount;

    @JsonCreator
    public GoalRequest(@JsonProperty("player") String player, @JsonProperty("goal") String goal, @JsonProperty("timeInDays") int time, @JsonProperty("amount") Money amount) {
        this.player = player;
        this.goal = goal;
        this.time = time;
        this.amount = amount;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    public int getTimeInDays() {
        return time;
    }

    public String getGoal() {
        return goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalRequest that = (GoalRequest) o;

        if (time != that.time) return false;
        if (!amount.equals(that.amount)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + time;
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "goal:builder:" + player + ":" + goal + ":" + time + ":" + amount;
    }
}
