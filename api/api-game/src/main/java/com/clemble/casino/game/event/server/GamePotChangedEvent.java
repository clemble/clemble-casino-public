package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potChanged")
public class GamePotChangedEvent extends GamePotEvent {

    /**
     * Generated 7/02/14
     */
    private static final long serialVersionUID = 4602160261299590501L;

    final private GameSessionKey nextSession;

    @JsonCreator
    public GamePotChangedEvent(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("nextSession") GameSessionKey nextSession) {
        super(sessionKey);
        this.nextSession = nextSession;
    }

    public GameSessionKey getNextSession() {
        return nextSession;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((nextSession == null) ? 0 : nextSession.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        GamePotChangedEvent other = (GamePotChangedEvent) obj;
        if (nextSession == null) {
            if (other.nextSession != null)
                return false;
        } else if (!nextSession.equals(other.nextSession))
            return false;
        return true;
    }

}
