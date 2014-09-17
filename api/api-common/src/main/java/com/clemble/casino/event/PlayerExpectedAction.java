package com.clemble.casino.event;

import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Transient;

@JsonTypeName("player:expected:event")
public class PlayerExpectedAction implements PlayerEvent {


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

    public boolean isExpected(Class<? extends PlayerEvent> expected) {
        return event.isAssignableFrom(expected);
    }

    public static PlayerExpectedAction fromClass(String player, Class<? extends PlayerEvent> action) {
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


}
