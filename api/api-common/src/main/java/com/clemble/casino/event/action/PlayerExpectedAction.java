package com.clemble.casino.event.action;

import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.clemble.casino.utils.ExpectedEventUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Transient;

@JsonTypeName(PlayerExpectedAction.JSON_TYPE)
public class PlayerExpectedAction implements PlayerAction {

    final public static String JSON_TYPE = "player:expected:event";

    /**
     * Generated 02/07/13
     */
    private static final long serialVersionUID = 6497446081286294728L;

    final private String player;
    final private String action;
    @Transient
    final private Class<? extends PlayerEvent> event;

    @JsonCreator
    public PlayerExpectedAction(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("action") String action) {
        this.player = player;
        this.action = action;
        this.event = ExpectedEventUtils.fromActionName(action);
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getAction() {
        return action;
    }

    public Class<? extends PlayerEvent> expected(){
        return event;
    }

    public <T extends Event & PlayerAware> boolean isExpected(Class<T> expected) {
        return event.isAssignableFrom(expected);
    }

    public static <T extends Event & PlayerAware> PlayerExpectedAction fromClass(String player, Class<T> action) {
        return new PlayerExpectedAction(player, ExpectedEventUtils.toActionName(action));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        return player.equals(other.player);
    }

    @Override
    public String toString() {
        return player + " > " + JSON_TYPE + " > " + action;
    }

}
