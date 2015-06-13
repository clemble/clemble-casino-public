package com.clemble.casino.player.event;

import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.player.PlayerConnection;
import com.clemble.casino.player.PlayerConnectionAware;
import com.clemble.casino.player.notification.PlayerConnectionAddNotification;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerConnectionAddEvent.JSON_TYPE)
public class PlayerConnectionAddEvent implements PlayerConnectionAware, PlayerNotificationConvertible, PlayerEvent {

    final public static String JSON_TYPE = "player:connection:add";

    final private String player;
    final private PlayerConnection connection;

    @JsonCreator
    public PlayerConnectionAddEvent(@JsonProperty(PLAYER) String player, @JsonProperty("connection") PlayerConnection connection) {
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
        return PlayerConnectionAddNotification.create(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConnectionAddEvent that = (PlayerConnectionAddEvent) o;

        if (!connection.equals(that.connection)) return false;
        return player.equals(that.player);

    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + connection.hashCode();
        return result;
    }

}
