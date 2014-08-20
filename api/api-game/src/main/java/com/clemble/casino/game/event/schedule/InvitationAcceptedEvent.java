package com.clemble.casino.game.event.schedule;

import com.clemble.casino.game.GameSessionAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("accepted")
public class InvitationAcceptedEvent implements InvitationResponseEvent {

    /**
     * Generated 02/06/2013
     */
    private static final long serialVersionUID = -4465974655141746411L;

    final private String player;
    final private String sessionKey;

    @JsonCreator
    public InvitationAcceptedEvent(@JsonProperty(PLAYER) String player, @JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey) {
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
        InvitationAcceptedEvent other = (InvitationAcceptedEvent) obj;
        return player.equals(other.player);
    }

    @Override
    public String toString(){
        return "accepted:" + player + ":" + sessionKey;
    }

}
