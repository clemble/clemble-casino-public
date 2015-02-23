package com.clemble.casino.player.event;

import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.player.PlayerConnectionAware;
import com.clemble.casino.player.notification.PlayerConnectedNotification;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerConnectionAddEvent.JSON_TYPE)
public class PlayerConnectionAddEvent implements PlayerConnectionAware, PlayerNotificationConvertible, PlayerEvent {

    final public static String JSON_TYPE = "player:connection:add";

    final private String player;
    final private String connection;

    @JsonCreator
    public PlayerConnectionAddEvent(@JsonProperty(PLAYER) String player, @JsonProperty("connection") String connection) {
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
    public PlayerNotification toNotification() {
        return PlayerConnectedNotification.create(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConnectionAddEvent that = (PlayerConnectionAddEvent) o;

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
