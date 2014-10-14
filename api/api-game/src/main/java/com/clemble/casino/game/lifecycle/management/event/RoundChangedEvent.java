package com.clemble.casino.game.lifecycle.management.event;

import java.util.Collection;

import com.clemble.casino.game.lifecycle.management.RoundGameState;
import com.clemble.casino.game.lifecycle.management.RoundState;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(RoundChangedEvent.JSON_TYPE)
public class RoundChangedEvent<State extends RoundState> extends RoundEvent {

    final public static String JSON_TYPE = "game:round:changed";

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -7618933855585060716L;

    final private Collection<PlayerAction> actions;

    @JsonCreator
    public RoundChangedEvent(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("state") RoundGameState state,
        @JsonProperty("actions") Collection<PlayerAction> actions) {
        super(sessionKey, state);
        this.actions = actions;
    }

    public Collection<PlayerAction> getActions() {
        return actions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((actions == null) ? 0 : actions.hashCode());
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
        RoundChangedEvent other = (RoundChangedEvent) obj;
        if (actions == null) {
            if (other.actions != null)
                return false;
        } else if (!actions.equals(other.actions))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return getSessionKey() + " > " + JSON_TYPE + " > " + actions;
    }

}
