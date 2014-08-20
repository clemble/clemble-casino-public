package com.clemble.casino.base;

import com.clemble.casino.event.PlayerAwareEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.data.annotation.Transient;

@JsonTypeName("expected")
public class ExpectedEvent implements PlayerAwareEvent {


    /**
     * Generated 02/07/13
     */
    private static final long serialVersionUID = 6497446081286294728L;

    final private String player;
    final private String action;
    @Transient
    final private Class<? extends PlayerAwareEvent> event;

    @JsonCreator
    public ExpectedEvent(
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

    public Class<? extends PlayerAwareEvent> expected(){
        return event;
    }

    public boolean isExpected(Class<? extends PlayerAwareEvent> expected) {
        return event.isAssignableFrom(expected);
    }

    public static ExpectedEvent fromClass(String player, Class<? extends PlayerAwareEvent> action) {
        return new ExpectedEvent(player, ExpectedEventUtils.toActionName(action));
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
        ExpectedEvent other = (ExpectedEvent) obj;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        return player.equals(other.player);
    }


}
