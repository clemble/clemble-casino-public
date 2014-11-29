package com.clemble.casino.player.notification;

import com.clemble.casino.notification.PlayerNotification;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerDiscoveredNotification.JSON_TYPE)
public class PlayerDiscoveredNotification implements PlayerConnectionNotification {

    final public static String JSON_TYPE = "notification:player:discovered";

    final private String player;
    final private String connection;

    @JsonCreator
    public PlayerDiscoveredNotification(@JsonProperty("player") String player, @JsonProperty("connection") String connection) {
        this.player = player;
        this.connection = connection;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getConnection() {
        return connection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerDiscoveredNotification that = (PlayerDiscoveredNotification) o;

        if (!connection.equals(that.connection)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + connection.hashCode();
        return result;
    }

}
