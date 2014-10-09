package com.clemble.casino.game.lifecycle.initiation.event;

import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/17/14.
 */
@JsonTypeName("game:initiation:complete")
public class GameInitiationCompleteEvent implements GameInitiationEvent {

    final public static String JSON_TYPE = "game:initiation:complete";

    final private String sessionKey;
    final private GameInitiation initiation;

    @JsonCreator
    public GameInitiationCompleteEvent(@JsonProperty(SESSION_KEY) String sessionKey, @JsonProperty("initiation") GameInitiation initiation) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameInitiationCompleteEvent that = (GameInitiationCompleteEvent) o;

        if (!initiation.equals(that.initiation)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey.hashCode();
        result = 31 * result + initiation.hashCode();
        return result;
    }

    public String toString() {
        return getSessionKey() + " > " + JSON_TYPE + " > " + initiation;
    }
}
