package com.clemble.casino.game.lifecycle.initiation.event;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(GameInitiationConfirmedEvent.JSON_TYPE)
public class GameInitiationConfirmedEvent implements GameInitiationEvent, PlayerAware {

    final public static String JSON_TYPE = "game:initiation:confirmed";

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -2483272558882430664L;

    final private String player;
    final private String sessionKey;
    final private GameInitiation initiation;

    @JsonCreator
    public GameInitiationConfirmedEvent(
        @JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey,
        @JsonProperty("initiation") GameInitiation initiation,
        @JsonProperty(PLAYER) String player) {
        this.sessionKey = sessionKey;
        this.initiation = initiation;
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
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

        GameInitiationConfirmedEvent that = (GameInitiationConfirmedEvent) o;

        if (!initiation.equals(that.initiation)) return false;
        if (!player.equals(that.player)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + sessionKey.hashCode();
        result = 31 * result + initiation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return sessionKey + ":" + player + " > " +  JSON_TYPE;
    }

}
