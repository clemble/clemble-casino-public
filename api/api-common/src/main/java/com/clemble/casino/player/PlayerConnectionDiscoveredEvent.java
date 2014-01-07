package com.clemble.casino.player;

import com.clemble.casino.event.PlayerAwareEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("connectionDiscovered")
public class PlayerConnectionDiscoveredEvent implements PlayerAwareEvent {

    /**
     * Generated 07/01/14
     */
    private static final long serialVersionUID = 1039045766016769682L;

    final private String player;
    final private String connection;

    @JsonCreator
    public PlayerConnectionDiscoveredEvent(@JsonProperty("player") String player, @JsonProperty("connection") String connection) {
        this.player = player;
        this.connection = connection;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getConnection() {
        return connection;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((connection == null) ? 0 : connection.hashCode());
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
        PlayerConnectionDiscoveredEvent other = (PlayerConnectionDiscoveredEvent) obj;
        if (connection == null) {
            if (other.connection != null)
                return false;
        } else if (!connection.equals(other.connection))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "connection:" + player + "->" + connection;
    }

}
