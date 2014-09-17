package com.clemble.casino.game.construction.event;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.construction.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:initiation:created")
public class GameInitiationCreatedEvent implements GameInitiationEvent {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = 1894571231006850214L;

    public GameInitiationCreatedEvent(GameInitiation initiation) {
        this(initiation.getSessionKey(), initiation);
    }

    final private String sessionKey;
    final private GameInitiation initiation;

    @JsonCreator
    public GameInitiationCreatedEvent(@JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey, @JsonProperty("initiation") GameInitiation initiation) {
        this.sessionKey = sessionKey;
        this.initiation = initiation;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public GameInitiation getInitiation() {
        return initiation;
    }

    @Override
    public String toString() {
        return sessionKey + " > game:initiation:created";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInitiationCreatedEvent that = (GameInitiationCreatedEvent) o;

        if (initiation != null ? !initiation.equals(that.initiation) : that.initiation != null) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (initiation != null ? initiation.hashCode() : 0);
        return result;
    }
}
