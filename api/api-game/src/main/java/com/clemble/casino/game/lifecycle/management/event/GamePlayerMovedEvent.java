package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(GamePlayerMovedEvent.JSON_TYPE)
public class GamePlayerMovedEvent implements GameManagementEvent, PlayerAware {

    final public static String JSON_TYPE = "game:management:player:moved";

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -4497503502857646005L;

    final private String player;
    final private String sessionKey;

    @JsonCreator
    public GamePlayerMovedEvent(@JsonProperty(SESSION_KEY) String sessionKey, @JsonProperty(PLAYER) String player) {
        this.sessionKey = sessionKey;
        this.player = player;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamePlayerMovedEvent that = (GamePlayerMovedEvent) o;

        if (!player.equals(that.player)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + sessionKey.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return sessionKey + ":" + player + " > " + JSON_TYPE;
    }

}
