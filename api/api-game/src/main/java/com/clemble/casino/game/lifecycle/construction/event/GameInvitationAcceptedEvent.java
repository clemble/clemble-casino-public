package com.clemble.casino.game.lifecycle.construction.event;

import com.clemble.casino.game.GameSessionAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:invitation:accepted")
public class GameInvitationAcceptedEvent implements GameInvitationResponseEvent {

    /**
     * Generated 02/06/2013
     */
    private static final long serialVersionUID = -4465974655141746411L;

    final private String player;
    final private String sessionKey;

    @JsonCreator
    public GameInvitationAcceptedEvent(@JsonProperty(PLAYER) String player, @JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey) {
        this.sessionKey = sessionKey;
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
    public int hashCode() {
        return player.hashCode() + sessionKey.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameInvitationAcceptedEvent other = (GameInvitationAcceptedEvent) obj;
        return player.equals(other.player);
    }

    @Override
    public String toString(){
        return "accepted:" + player + ":" + sessionKey;
    }

}
