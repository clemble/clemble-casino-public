package com.clemble.casino.goal.lifecycle.configuration.rule.due;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/6/14.
 */
public class GoalDueRule implements GoalRule {

    final private String providerId;

    @JsonCreator
    public GoalDueRule(@JsonProperty("providerId") String providerId) {
        this.providerId = providerId;
    }

    public String getProviderId() {
        return providerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalDueRule that = (GoalDueRule) o;

        if (providerId != null ? !providerId.equals(that.providerId) : that.providerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return providerId != null ? providerId.hashCode() : 0;
    }
}
