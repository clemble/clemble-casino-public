package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.TournamentGameState;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(TournamentEndedEvent.JSON_TYPE)
public class TournamentEndedEvent extends TournamentEvent implements GameEndedEvent {

    final public static String JSON_TYPE = "game:management:tournament:ended";

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = -8432784863604445232L;

    final private Outcome outcome;
    final private TournamentGameState state;

    @JsonCreator
    public TournamentEndedEvent(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("outcome") Outcome outcome,
        @JsonProperty("context") TournamentGameState state) {
        super(sessionKey);
        this.outcome = outcome;
        this.state = state;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
    }

    public TournamentGameState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TournamentEndedEvent that = (TournamentEndedEvent) o;

        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (outcome != null ? !outcome.equals(that.outcome) : that.outcome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getSessionKey() + " > " + JSON_TYPE;
    }

}
