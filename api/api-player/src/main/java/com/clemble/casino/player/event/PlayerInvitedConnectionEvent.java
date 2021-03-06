package com.clemble.casino.player.event;

import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.player.PlayerConnection;
import com.clemble.casino.player.PlayerConnectionAware;
import com.clemble.casino.player.notification.PlayerInvitedNotification;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 12/12/14.
 */
public class PlayerInvitedConnectionEvent implements PlayerEvent, PlayerConnectionAware, PlayerNotificationConvertible {

    final public static String JSON_TYPE = "player:connection:invited";

    /**
     * Generated 07/01/14
     */
    private static final long serialVersionUID = 1039045766016769682L;

    final private String player;
    final private PlayerConnection connection;

    @JsonCreator
    public PlayerInvitedConnectionEvent(
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
    public PlayerNotification toNotification() {
        return PlayerInvitedNotification.create(this);
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
        PlayerInvitedConnectionEvent other = (PlayerInvitedConnectionEvent) obj;
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
        return player + " > " + JSON_TYPE + " > " + connection;
    }

}
