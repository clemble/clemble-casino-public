package com.clemble.casino.game.lifecycle.construction.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(GameConstructionCanceledEvent.JSON_TYPE)
public class GameConstructionCanceledEvent implements GameConstructionEvent {

    final public static String JSON_TYPE = "game:construction:canceled";

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 1L;

    final private String sessionKey;

    @JsonCreator
    public GameConstructionCanceledEvent(@JsonProperty(SESSION_KEY) String session) {
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
        return sessionKey.equals(((GameConstructionCanceledEvent) obj).sessionKey);
    }

    @Override
    public String toString() {
        return sessionKey + " > " + JSON_TYPE;
    }

}
