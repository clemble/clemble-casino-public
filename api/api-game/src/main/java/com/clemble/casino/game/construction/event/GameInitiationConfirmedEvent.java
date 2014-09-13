package com.clemble.casino.game.construction.event;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("confirmed")
public class GameInitiationConfirmedEvent extends GameInitiationEvent implements PlayerAware {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -2483272558882430664L;

    final private String player;

    @JsonCreator
    public GameInitiationConfirmedEvent(
        @JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey,
        @JsonProperty("initiation") GameInitiation initiation,
        @JsonProperty(PLAYER) String player) {
        super(sessionKey, initiation);
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        GameInitiationConfirmedEvent other = (GameInitiationConfirmedEvent) obj;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "initiationConfirmed:" + player + ":" + super.toString();
    }

}
