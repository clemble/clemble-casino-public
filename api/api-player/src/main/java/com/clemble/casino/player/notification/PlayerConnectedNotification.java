package com.clemble.casino.player.notification;

import com.clemble.casino.player.event.PlayerConnectedEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerConnectedNotification.JSON_TYPE)
public class PlayerConnectedNotification implements PlayerConnectionNotification {

    final public static String JSON_TYPE = "notification:player:connected";

    final private String key;
    final private String player;
    final private String connection;
    final private Date created;

    @JsonCreator
    public PlayerConnectedNotification(
        @JsonProperty("key") String key,
        @JsonProperty("player") String player,
        @JsonProperty("connection")String connection,
        @JsonProperty("created") Date created) {
        this.key = key;
        this.player = player;
        this.connection = connection;
        this.created = created;
    }

    @JsonProperty
    public String getKey() {
        return key;
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
    public Date getCreated() {
        return created;
    }

    public static PlayerConnectedNotification create(PlayerConnectedEvent connectedEvent) {
        String key = connectedEvent.getPlayer() + ":" + connectedEvent.getConnection();
        return new PlayerConnectedNotification(key, connectedEvent.getPlayer(), connectedEvent.getConnection(), new Date());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConnectedNotification that = (PlayerConnectedNotification) o;

        if (!created.equals(that.created)) return false;
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
