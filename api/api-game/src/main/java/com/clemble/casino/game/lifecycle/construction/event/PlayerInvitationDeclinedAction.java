package com.clemble.casino.game.lifecycle.construction.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PlayerInvitationDeclinedAction.JSON_TYPE)
public class PlayerInvitationDeclinedAction implements PlayerInvitationAction {

    final public static String JSON_TYPE = "game:construction:invitation:declined";

    /**
     * Generated 02/06/13
     */
    private static final long serialVersionUID = 655381424177654890L;

    final private String player;
    final private String sessionKey;

    @JsonCreator
    public PlayerInvitationDeclinedAction(@JsonProperty(PLAYER) String player, @JsonProperty(SESSION_KEY) String session) {
        this.player = player;
        this.sessionKey = session;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (player == null ? 0 : player.hashCode());
        result = prime * result + (int) (sessionKey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerInvitationDeclinedAction other = (PlayerInvitationDeclinedAction) obj;
        if (!sessionKey.equals(other.sessionKey))
            return false;
        return player.equals(other.player);
    }

    @Override
    public String toString(){
        return sessionKey + ":" + player + " > " + JSON_TYPE;
    }

}
