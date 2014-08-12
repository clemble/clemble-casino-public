package com.clemble.casino.goal;

import com.clemble.casino.payment.PaymentTransactionAwareConvertible;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 8/11/14.
 */
public class GoalKey implements PlayerAware, PaymentTransactionAwareConvertible {

    final private String player;
    final private String goal;

    @JsonCreator
    public GoalKey(@JsonProperty("player") String player, @JsonProperty("goal") String goal) {
        this.player = player;
        this.goal = goal;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getGoal() {
        return goal;
    }

    @Override
    public PaymentTransactionKey toPaymentTransactionKey() {
        return new PaymentTransactionKey(player, goal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalKey goalKey = (GoalKey) o;

        if (!goal.equals(goalKey.goal)) return false;
        if (!player.equals(goalKey.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + goal.hashCode();
        return result;
    }

}
