package com.clemble.casino.payment.bonus;

import com.clemble.casino.goal.GoalAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 5/10/15.
 */
@JsonTypeName(GoalReachedBonusPaymentSource.JSON_TYPE)
public class GoalReachedBonusPaymentSource implements BonusPaymentSource, GoalAware {

    final public static String JSON_TYPE = "payment:bonus:goal:reached";

    final private String goalKey;

    @JsonCreator
    public GoalReachedBonusPaymentSource(@JsonProperty(GOAL_KEY) String goalKey) {
        this.goalKey = goalKey;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String toTransactionKey(String player) {
        return goalKey + ":reachedbonus";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalReachedBonusPaymentSource)) return false;

        GoalReachedBonusPaymentSource that = (GoalReachedBonusPaymentSource) o;

        return goalKey.equals(that.goalKey);

    }

    @Override
    public int hashCode() {
        return goalKey.hashCode();
    }

}
