package com.clemble.casino.event.action;

import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.clemble.casino.utils.ExpectedEventUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Transient;

@JsonTypeName(PlayerExpectedAction.JSON_TYPE)
public class PlayerExpectedAction implements Action {

    final public static String JSON_TYPE = "player:expected:event";

    /**
     * Generated 02/07/13
     */
    private static final long serialVersionUID = 6497446081286294728L;

    final private String action;
    @Transient
    final private Class<? extends PlayerEvent> event;

    @JsonCreator
    public PlayerExpectedAction(
        @JsonProperty("action") String action) {
        this.action = action;
        this.event = ExpectedEventUtils.fromActionName(action);
    }

    public String getAction() {
        return action;
    }

    public Class<? extends PlayerEvent> expected(){
        return event;
    }

    public <T extends Event> boolean isExpected(Class<T> expected) {
        return event.isAssignableFrom(expected);
    }

    public static <T extends Event> PlayerExpectedAction fromClass(Class<T> action) {
        return new PlayerExpectedAction(ExpectedEventUtils.toActionName(action));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
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
        PlayerExpectedAction other = (PlayerExpectedAction) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return JSON_TYPE + " > " + action;
    }

}
