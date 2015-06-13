package com.clemble.casino.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 11/10/14.
 */
public class PlayerConnectionInvitation implements PlayerAware, PlayerConnectionAware {

    final private String player;
    final private PlayerConnection connection;

    @JsonCreator
    public PlayerConnectionInvitation(
            @JsonProperty(PLAYER) String player,
            @JsonProperty("connection") PlayerConnection connection) {
        this.player = player;
        this.connection = connection;
    }


    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public PlayerConnection getConnection() {
        return connection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConnectionInvitation that = (PlayerConnectionInvitation) o;

        return player.equals(that.player);
    }

    @Override
    public int hashCode() {
        return player.hashCode();
    }

}
