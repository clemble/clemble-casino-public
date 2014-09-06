package com.clemble.casino.event;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("default")
public class DefaultPlayerEvent implements PlayerAwareEvent {

    /**
     * Generated 23/12/13
     */
    private static final long serialVersionUID = 6526877866632872028L;

    final private String player;

    @JsonCreator
    public DefaultPlayerEvent(@JsonProperty(PlayerAware.PLAYER) String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DefaultPlayerEvent)) return false;

        DefaultPlayerEvent that = (DefaultPlayerEvent) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player != null ? player.hashCode() : 0;
    }
}
