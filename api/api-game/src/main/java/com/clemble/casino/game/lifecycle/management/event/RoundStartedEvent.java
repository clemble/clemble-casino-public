package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.RoundGameState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(RoundStartedEvent.JSON_TYPE)
public class RoundStartedEvent<State extends RoundGameState> extends RoundEvent implements GameStartedEvent {

    final public static String JSON_TYPE = "game:management:round:started";

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
        return getSessionKey() + JSON_TYPE;
    }

}
