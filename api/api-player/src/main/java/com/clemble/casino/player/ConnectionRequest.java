package com.clemble.casino.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 11/10/14.
 */
public class ConnectionRequest implements PlayerAware {

    final private String player;

    @JsonCreator
    public ConnectionRequest(@JsonProperty(PLAYER) String player) {
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConnectionRequest that = (ConnectionRequest) o;

        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

}
