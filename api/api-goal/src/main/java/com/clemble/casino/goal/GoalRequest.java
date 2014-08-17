package com.clemble.casino.goal;

import com.clemble.casino.money.Money;
import com.clemble.casino.payment.AmountAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/16/14.
 */
public class GoalRequest implements GoalDescriptionAware, AmountAware {

    final private String goal;
    final private int time;
    final private Money amount;
    final private String judge;

    // TODO Consider removing player from GoalDescription, less errors during construction
    @JsonCreator
    public GoalRequest(@JsonProperty("judge") String judge, @JsonProperty("goal") String goal, @JsonProperty("timeInDays") int time, @JsonProperty("amount") Money amount) {
        this.goal = goal;
        this.time = time;
        this.judge = judge;
        this.amount = amount;
    }

    @Override
    public Money getAmount() {
        return amount;
    }

    public int getTimeInDays() {
        return time;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public String getJudge() {
        return judge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalRequest request = (GoalRequest) o;

        if (time != request.time) return false;
        if (amount != null ? !amount.equals(request.amount) : request.amount != null) return false;
        if (goal != null ? !goal.equals(request.goal) : request.goal != null) return false;
        if (judge != null ? !judge.equals(request.judge) : request.judge != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (goal != null ? goal.hashCode() : 0);
        result = 31 * result + time;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (judge != null ? judge.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "goal:builder:" + judge + ":" + goal + ":" + time + ":" + amount;
    }
}
