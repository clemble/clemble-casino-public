package com.clemble.casino.game.event.server;

import com.clemble.casino.game.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("round_started")
public class RoundStartedEvent<State extends RoundGameState> extends RoundEvent implements GameStartedEvent {

    /**
     * Generated
     */
    private static final long serialVersionUID = -4474960027054354888L;

    @JsonCreator
    public RoundStartedEvent(@JsonProperty(SESSION_KEY) String session, @JsonProperty("state") State state) {
        super(session, state);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (super.hashCode());
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
        RoundStartedEvent<?> other = (RoundStartedEvent<?>) obj;
        if (!super.equals(other))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "round:started:" + getSessionKey();
    }

}
