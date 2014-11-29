package com.clemble.casino.player.notification;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerInvitedNotification.JSON_TYPE)
public class PlayerInvitedNotification implements PlayerConnectionNotification {

    final public static String JSON_TYPE = "notification:player:invited";

    final private String player;
    final private String connection;

    @JsonCreator
    public PlayerInvitedNotification(@JsonProperty(PLAYER) String player, @JsonProperty("connection") String connection) {
        this.player = player;
        this.connection = connection;
    }

    @Override
    public String getConnection() {
        return connection;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerInvitedNotification that = (PlayerInvitedNotification) o;

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
