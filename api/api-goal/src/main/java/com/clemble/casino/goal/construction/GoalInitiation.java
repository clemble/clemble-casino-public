package com.clemble.casino.goal.construction;

import com.clemble.casino.construction.Initiation;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by mavarazy on 9/12/14.
 */
public class GoalInitiation implements Initiation<GoalConfiguration> {

    final private Date startDate;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalInitiation(@JsonProperty("configuration") GoalConfiguration configuration, @JsonProperty("startDate") Date startDate) {
        this.configuration = configuration;
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiation that = (GoalInitiation) o;

        if (!configuration.equals(that.configuration)) return false;
        if (!startDate.equals(that.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startDate.hashCode();
        result = 31 * result + configuration.hashCode();
        return result;
    }
}
