package com.clemble.casino.game.lifecycle.construction.event;

import com.clemble.casino.game.lifecycle.construction.PlayerGameConstructionRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(GameConstructionPlayerInvitedEvent.JSON_TYPE)
public class GameConstructionPlayerInvitedEvent implements GameConstructionEvent {

    final public static String JSON_TYPE = "game:construction:invitation";

    /**
     * Generated 02/06/2013
     */
    private static final long serialVersionUID = 1753173974867187325L;

    final private String sessionKey;
    final private PlayerGameConstructionRequest gameRequest;

    @JsonCreator
    public GameConstructionPlayerInvitedEvent(@JsonProperty(SESSION_KEY) String session, @JsonProperty("gameRequest") PlayerGameConstructionRequest request) {
        this.sessionKey = session;
        this.gameRequest = request;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    public PlayerGameConstructionRequest getGameRequest() {
        return gameRequest;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((gameRequest == null) ? 0 : gameRequest.hashCode());
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
        GameConstructionPlayerInvitedEvent other = (GameConstructionPlayerInvitedEvent) obj;
        if (gameRequest == null) {
            if (other.gameRequest != null)
                return false;
        } else if (!gameRequest.equals(other.gameRequest))
            return false;
        return sessionKey.equals(other.sessionKey);
    }
    
    @Override
    public String toString() {
        return sessionKey + " > " + JSON_TYPE;
    }

}
