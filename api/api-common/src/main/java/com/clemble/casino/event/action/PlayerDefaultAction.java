package com.clemble.casino.event.action;

import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName(PlayerDefaultAction.JSON_TYPE)
public class PlayerDefaultAction implements Action {

    final public static String JSON_TYPE = "player:default:action";

    /**
     * Generated 23/12/13
     */
    private static final long serialVersionUID = 6526877866632872028L;

    @JsonCreator
    public PlayerDefaultAction() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDefaultAction)) return false;

        PlayerDefaultAction that = (PlayerDefaultAction) o;

        return true;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return JSON_TYPE;
    }

}
