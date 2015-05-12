package com.clemble.casino.goal;

import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.lifecycle.management.event.GoalEndedEvent;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;
import com.clemble.casino.money.Money;
import com.clemble.casino.payment.PaymentSource;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeName(GoalPaymentSource.JSON_TYPE)
public class GoalPaymentSource implements PaymentSource, GoalAware, OutcomeAware, PlayerAware {

    final public static String JSON_TYPE = "payment:goal";

    final private String goalKey;
    final private String goal;
    final private String player;
    final private Outcome outcome;

    @JsonCreator
    public GoalPaymentSource(
        @JsonProperty(GOAL_KEY) String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("goal") String goal,
        @JsonProperty("outcome") Outcome outcome) {
        this.goal = goal;
        this.player = player;
        this.goalKey = goalKey;
        this.outcome = outcome;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    public String getGoal() {
        return goal;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public String toTransactionKey(String player) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalPaymentSource that = (GoalPaymentSource) o;

        if (!goalKey.equals(that.goalKey)) return false;
        if (!outcome.equals(that.outcome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + outcome.hashCode();
        return result;
    }

    public static GoalPaymentSource create(GoalState state) {
        return new GoalPaymentSource(
            state.getGoalKey(),
            state.getPlayer(),
            state.getGoal(),
            state.getOutcome()
        );
    }
}
