package com.clemble.casino.goal.lifecycle.configuration.rule.parts;

import com.clemble.casino.goal.GoalPartsAware;
import com.clemble.casino.goal.lifecycle.configuration.rule.GoalRule;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalPartsRule implements GoalRule, GoalPartsAware {

    final private int parts;

    @JsonCreator
    public GoalPartsRule(@JsonProperty("parts") int parts) {
        this.parts = parts;
    }

    @Override
    public int getParts(){
        return parts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalPartsRule that = (GoalPartsRule) o;

        if (parts != that.parts) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return parts;
    }

    @Override
    public String toString() {
        return "rule:goal:parts" + " > " + parts;
    }

}
