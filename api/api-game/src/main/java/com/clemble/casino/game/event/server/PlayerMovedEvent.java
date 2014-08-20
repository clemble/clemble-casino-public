package com.clemble.casino.game.event.server;

import com.clemble.casino.event.PlayerAwareEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("moved")
public class PlayerMovedEvent extends GameManagementEvent implements PlayerAwareEvent {

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -4497503502857646005L;

    final private String player;

    @JsonCreator
    public PlayerMovedEvent(@JsonProperty(SESSION_KEY) String sessionKey, @JsonProperty(PLAYER) String player) {
        super(sessionKey);
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
        PlayerMovedEvent other = (PlayerMovedEvent) obj;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "moved:" + player;
    }

}
