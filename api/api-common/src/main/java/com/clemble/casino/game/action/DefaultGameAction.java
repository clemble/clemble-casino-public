package com.clemble.casino.game.action;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("default")
public class DefaultGameAction implements GameAction {

    /**
     * Generated 23/12/13
     */
    private static final long serialVersionUID = 6526877866632872028L;

    final private String player;

    @JsonCreator
    public DefaultGameAction(@JsonProperty("player") String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultGameAction)) return false;

        DefaultGameAction that = (DefaultGameAction) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
