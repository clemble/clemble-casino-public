package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 2/23/15.
 */
public class GoalSuggestionResponse implements GoalConfigurationAware {

    final private GoalConfiguration configuration;
    final private boolean accepted;

    @JsonCreator
    public GoalSuggestionResponse(
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("accepted") boolean accepted) {
        this.configuration = configuration;
        this.accepted = accepted;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    public boolean getAccepted() {
        return accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalSuggestionResponse)) return false;

        GoalSuggestionResponse that = (GoalSuggestionResponse) o;

        if (accepted != that.accepted) return false;
        if (!configuration.equals(that.configuration)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = configuration.hashCode();
        result = 31 * result + (accepted ? 1 : 0);
        return result;
    }

}
