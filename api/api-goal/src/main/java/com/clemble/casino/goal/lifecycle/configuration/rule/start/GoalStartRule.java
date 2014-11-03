package com.clemble.casino.goal.lifecycle.configuration.rule.start;

import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 17/10/14.
 *
 * TODO This is quick workaround think of a better name for this
 */
public class GoalStartRule implements GoalRule {

    final private long timeout;

    @JsonCreator
    public GoalStartRule(@JsonProperty("timeout") long timeout) {
        this.timeout = timeout;
    }

    public long getTimeout() {
        return timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalStartRule)) return false;

        GoalStartRule that = (GoalStartRule) o;

        if (timeout != that.timeout) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (timeout ^ (timeout >>> 32));
    }
}
