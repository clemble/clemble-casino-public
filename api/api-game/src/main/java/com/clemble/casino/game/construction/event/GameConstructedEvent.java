package com.clemble.casino.game.construction.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("gameConstructed")
public class GameConstructedEvent implements GameConstructionEvent {

    /**
     * Generated 24/06/13
     */
    private static final long serialVersionUID = 1069615920429317027L;

    final private String sessionKey;

    @JsonCreator
    public GameConstructedEvent(@JsonProperty(SESSION_KEY) String session) {
        this.sessionKey = session;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public int hashCode() {
        return sessionKey.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameConstructedEvent other = (GameConstructedEvent) obj;
        return sessionKey.equals(other.sessionKey);
    }

    @Override
    public String toString() {
        return "constructed:" + sessionKey;
    }

}
