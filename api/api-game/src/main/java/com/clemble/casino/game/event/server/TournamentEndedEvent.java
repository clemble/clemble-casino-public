package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.TournamentGamePlayerContext;
import com.clemble.casino.game.outcome.GameOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("tournamentEnded")
public class TournamentEndedEvent extends TournamentEvent implements GameEndedEvent<TournamentGamePlayerContext> {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = -8432784863604445232L;

    final private GameOutcome outcome;
    final private GameContext<TournamentGamePlayerContext> context;

    @JsonCreator
    public TournamentEndedEvent(@JsonProperty(GameSessionAware.SESSION_KEY) GameSessionKey sessionKey, @JsonProperty("outcome") GameOutcome outcome, @JsonProperty("context") GameContext<TournamentGamePlayerContext> context) {
        super(sessionKey);
        this.outcome = outcome;
        this.context = context;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
    }

    @Override
    public GameContext<TournamentGamePlayerContext> getContext() {
        return context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TournamentEndedEvent that = (TournamentEndedEvent) o;

        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (outcome != null ? !outcome.equals(that.outcome) : that.outcome != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        return result;
    }
}
