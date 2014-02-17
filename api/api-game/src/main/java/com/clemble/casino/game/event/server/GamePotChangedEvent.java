package com.clemble.casino.game.event.server;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.PotGameContext;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potChanged")
public class GamePotChangedEvent extends GamePotEvent {

    /**
     * Generated 7/02/14
     */
    private static final long serialVersionUID = 4602160261299590501L;

    final private GameSessionKey nextSession;

    @JsonCreator
    public GamePotChangedEvent(@JsonProperty("session") GameSessionKey sessionKey,
            @JsonProperty("context") PotGameContext context,
            @JsonProperty("nextSession") GameSessionKey nextSession) {
        super(sessionKey, context);
        this.nextSession = nextSession;
    }

    public GameSessionKey getNextSession() {
        return nextSession;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
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
        GamePotChangedEvent other = (GamePotChangedEvent) obj;
        return true;
    }

    @Override
    public String toString(){
        return "potChanged:" + getSession();
    }

}
