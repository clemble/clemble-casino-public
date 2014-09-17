package com.clemble.casino.game.event;

import java.util.Collection;

import com.clemble.casino.game.RoundGameState;
import com.clemble.casino.game.action.PlayerGameAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:round:changed")
public class RoundChangedEvent<State extends RoundGameState> extends RoundEvent {

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -7618933855585060716L;

    final private Collection<? extends PlayerGameAction> actions;

    @JsonCreator
    public RoundChangedEvent(@JsonProperty(SESSION_KEY) String session, @JsonProperty("state") State state, @JsonProperty("actions") Collection<? extends PlayerGameAction> actions) {
        super(session, state);
        this.actions = actions;
    }

    public Collection<? extends PlayerGameAction> getActions() {
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
        return "round:changed:" + actions;
    }

}
