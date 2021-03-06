package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.lifecycle.construction.ConstructionRequest;
import com.clemble.casino.lifecycle.construction.ConstructionState;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.tag.TagUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * Created by mavarazy on 8/26/14.
 */
public class GoalConstructionRequest implements ConstructionRequest<GoalConfiguration>, GoalDescriptionAware {

    final private String goal;
    final private DateTimeZone timezone;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalConstructionRequest(
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("goal") String goal,
        @JsonProperty(TIME_ZONE) DateTimeZone timezone) {
        this.configuration = configuration;
        this.timezone = timezone;
        this.goal = goal;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public DateTimeZone getTimezone() {
        return timezone;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public GoalConstruction toConstruction(String player, String goalKey) {
        return new GoalConstruction(goalKey, player, goal, timezone, TagUtils.getTag(goal), DateTime.now(timezone), configuration, ConstructionState.pending);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalConstructionRequest that = (GoalConstructionRequest) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        return !(goal != null ? !goal.equals(that.goal) : that.goal != null);

    }

    @Override
    public int hashCode() {
        int result = 31 + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }
}
