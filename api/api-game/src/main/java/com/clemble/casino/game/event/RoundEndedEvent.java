package com.clemble.casino.game.event;

import com.clemble.casino.game.*;
import com.clemble.casino.game.RoundGamePlayerContext;
import com.clemble.casino.game.outcome.GameOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:round:ended")
public class RoundEndedEvent<State extends RoundGameState> extends RoundEvent implements GameEndedEvent<RoundGamePlayerContext> {

    /**
     * Generated 07/05/13
     */
    private static final long serialVersionUID = 820200145932972096L;

    final private GameOutcome outcome;
    final private GameContext<RoundGamePlayerContext> context;

    @JsonCreator
    public RoundEndedEvent(@JsonProperty(SESSION_KEY) String sessionKey,
                           @JsonProperty("state") RoundGameState state,
                           @JsonProperty("outcome") GameOutcome outcome,
                           @JsonProperty("context") GameContext<RoundGamePlayerContext> context) {
        super(sessionKey, state);
        this.outcome = outcome;
        this.context = context;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
    }

    @Override
    public GameContext<RoundGamePlayerContext> getContext() {
        return context;
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
        return "round:ended:" + getSessionKey() + ":" + outcome;
    }


    public static <State extends RoundGameState> RoundEndedEvent<State> fromContext(GameContext<RoundGamePlayerContext> context, State state, GameOutcome outcome) {
        return new RoundEndedEvent<State>(context.getSessionKey(), state, outcome, context);
    }

}
