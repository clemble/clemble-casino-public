package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.*;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(RoundEndedEvent.JSON_TYPE)
public class RoundEndedEvent extends RoundEvent implements GameEndedEvent {

    final public static String JSON_TYPE = "game:management:round:ended";

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = 820200145932972096L;

    final private Outcome outcome;

    @JsonCreator
    public RoundEndedEvent(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("state") RoundGameState state,
        @JsonProperty("outcome") Outcome outcome) {
        super(sessionKey, state);
        this.outcome = outcome;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((outcome == null) ? 0 : outcome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RoundEndedEvent other = (RoundEndedEvent) obj;
        if (outcome == null) {
            if (other.outcome != null)
                return false;
        } else if (!outcome.equals(other.outcome))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getSessionKey() + " > " + JSON_TYPE + " > " + outcome;
    }


    public static RoundEndedEvent fromContext(RoundGameState state, Outcome outcome) {
        return new RoundEndedEvent(state.getContext().getSessionKey(), state, outcome);
    }

}
