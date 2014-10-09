package com.clemble.casino.game.lifecycle.initiation.event;

import java.util.Set;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(GameInitiationCanceledEvent.JSON_TYPE)
public class GameInitiationCanceledEvent implements GameInitiationEvent {
    
    final public static String JSON_TYPE = "game:initiation:canceled";

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -2108886096839930339L;

    final private String sessionKey;
    final private GameInitiation initiation;
    final private Set<String> confirmed;

    @JsonCreator
    public GameInitiationCanceledEvent(@JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey,
            @JsonProperty("initiation") GameInitiation initiation,
            @JsonProperty("confirmed") Set<String> confirmed) {
        this.sessionKey = sessionKey;
        this.initiation = initiation;
        this.confirmed = confirmed;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public GameInitiation getInitiation() {
        return initiation;
    }

    public Set<String> getConfirmed(){
        return confirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInitiationCanceledEvent that = (GameInitiationCanceledEvent) o;

        if (confirmed != null ? !confirmed.equals(that.confirmed) : that.confirmed != null) return false;
        if (initiation != null ? !initiation.equals(that.initiation) : that.initiation != null) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey != null ? sessionKey.hashCode() : 0;
        result = 31 * result + (initiation != null ? initiation.hashCode() : 0);
        result = 31 * result + (confirmed != null ? confirmed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return sessionKey + " > " + JSON_TYPE + " > " + confirmed;
    }

}
