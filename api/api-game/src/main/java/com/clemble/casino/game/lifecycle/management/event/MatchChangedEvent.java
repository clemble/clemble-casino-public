package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.management.MatchGameContext;
import com.clemble.casino.game.lifecycle.management.MatchGameState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(MatchChangedEvent.JSON_TYPE)
public class MatchChangedEvent extends MatchEvent {

    final public static String JSON_TYPE = "game:management:match:changed";

    /**
     * Generated 7/02/14
     */
    private static final long serialVersionUID = 4602160261299590501L;

    final private String nextSession;
    final private GameInitiation nextInitiation;

    @JsonCreator
    public MatchChangedEvent(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("context") MatchGameState state,
        @JsonProperty("nextSession") String nextSession,
        @JsonProperty("nextInitiation") GameInitiation nextInitiation) {
        super(sessionKey, state);
        this.nextSession = nextSession;
        this.nextInitiation = nextInitiation;
    }

    public String getNextSession() {
        return nextSession;
    }

    public GameInitiation getNextInitiation() {
        return nextInitiation;
    }

    @Override
    public String toString(){
        return getSessionKey() + " > " + JSON_TYPE;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((nextSession == null) ? 0 : nextSession.hashCode());
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
        MatchChangedEvent other = (MatchChangedEvent) obj;
        if (nextSession == null) {
            if (other.nextSession != null)
                return false;
        } else if (!nextSession.equals(other.nextSession))
            return false;
        if (nextInitiation == null) {
            if (other.nextInitiation != null)
                return false;
        } else if (!nextInitiation.equals(other.nextInitiation))
            return false;
        return true;
    }

}
