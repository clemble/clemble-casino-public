package com.clemble.casino.game.event.server;

import java.util.Collection;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.MatchGameRecord;
import com.clemble.casino.game.action.GameAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("changed")
public class GameMatchStateChangedEvent extends GameMatchEvent {

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -7618933855585060716L;

    final private Collection<? extends GameAction> actions;

    public GameMatchStateChangedEvent(MatchGameRecord session, Collection<? extends GameAction> actions) {
        super(session);
        this.actions = actions;
    }

    @JsonCreator
    public GameMatchStateChangedEvent(@JsonProperty("session") GameSessionKey session, @JsonProperty("state") GameState state, @JsonProperty("actions") Collection<GameAction> actions) {
        super(session, state);
        this.actions = actions;
    }

    public Collection<? extends GameAction> getActions() {
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
        GameMatchStateChangedEvent other = (GameMatchStateChangedEvent) obj;
        if (actions == null) {
            if (other.actions != null)
                return false;
        } else if (!actions.equals(other.actions))
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "changed:" + actions;
    }

}
