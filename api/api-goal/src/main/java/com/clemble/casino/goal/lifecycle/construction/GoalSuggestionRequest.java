package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.TimeZoneAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTimeZone;

/**
 * Created by mavarazy on 1/3/15.
 */
public class GoalSuggestionRequest implements GoalDescriptionAware {

    final private String goal;
    final private DateTimeZone timezone;

    @JsonCreator
    public GoalSuggestionRequest(
        @JsonProperty("goal") String goal,
        @JsonProperty(TIME_ZONE) DateTimeZone timezone) {
        this.goal = goal;
        this.timezone = timezone;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public DateTimeZone getTimezone() {
        return timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalSuggestionRequest that = (GoalSuggestionRequest) o;

        return goal.equals(that.goal);

    }

    @Override
    public int hashCode() {
        int result = goal.hashCode();
        return result;
    }
}
