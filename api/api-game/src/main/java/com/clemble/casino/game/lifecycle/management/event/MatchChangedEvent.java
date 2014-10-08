package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.management.MatchGameContext;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:match:changed")
public class MatchChangedEvent extends MatchEvent {

    /**
     * Generated 7/02/14
     */
    private static final long serialVersionUID = 4602160261299590501L;

    final private String nextSession;

    @JsonCreator
    public MatchChangedEvent(@JsonProperty(SESSION_KEY) String sessionKey,
                             @JsonProperty("context") MatchGameContext context,
                             @JsonProperty("nextSession") String nextSession) {
        super(sessionKey, context);
        this.nextSession = nextSession;
    }

    public String getNextSession() {
        return nextSession;
    }

    @Override
    public String toString(){
        return "match:changed:" + getSessionKey();
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
        return true;
    }

}
