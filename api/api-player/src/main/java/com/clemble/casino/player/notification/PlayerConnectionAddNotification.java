package com.clemble.casino.player.notification;

import com.clemble.casino.player.PlayerConnection;
import com.clemble.casino.player.event.PlayerConnectionAddEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerConnectionAddNotification.JSON_TYPE)
public class PlayerConnectionAddNotification implements PlayerConnectionNotification {

    final public static String JSON_TYPE = "notification:player:connected";

    final private String key;
    final private String player;
    final private PlayerConnection connection;
    final private DateTime created;

    @JsonCreator
    public PlayerConnectionAddNotification(
            @JsonProperty("key") String key,
            @JsonProperty("player") String player,
            @JsonProperty("connection") PlayerConnection connection,
            @JsonProperty("created") DateTime created) {
        this.key = key;
        this.player = player;
        this.connection = connection;
        this.created = created;
    }

    @Override
    public String getKey() {
        return key;
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
    public DateTime getCreated() {
        return created;
    }

    public static PlayerConnectionAddNotification create(PlayerConnectionAddEvent connectedEvent) {
        String key = connectedEvent.getPlayer() + ":" + connectedEvent.getConnection();
        return new PlayerConnectionAddNotification(key, connectedEvent.getPlayer(), connectedEvent.getConnection(), DateTime.now(DateTimeZone.UTC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConnectionAddNotification that = (PlayerConnectionAddNotification) o;

        if (!created.equals(that.created)) return false;
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
