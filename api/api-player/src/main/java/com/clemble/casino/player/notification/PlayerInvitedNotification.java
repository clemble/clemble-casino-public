package com.clemble.casino.player.notification;

import com.clemble.casino.player.event.PlayerInvitedConnectionEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(PlayerInvitedNotification.JSON_TYPE)
public class PlayerInvitedNotification implements PlayerConnectionNotification {

    final public static String JSON_TYPE = "notification:player:invited";

    final private String key;
    final private String player;
    final private String connection;
    final private DateTime created;

    // TODO remove extra player reference
    @JsonCreator
    public PlayerInvitedNotification(
        @JsonProperty("key") String key,
        @JsonProperty(PLAYER) String player,
        @JsonProperty("connection") String connection,
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
    public String getConnection() {
        return connection;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    public static PlayerInvitedNotification create(PlayerInvitedConnectionEvent invitedEvent) {
        String key = invitedEvent.getPlayer() + ":" + invitedEvent.getConnection();
        return new PlayerInvitedNotification(key, invitedEvent.getPlayer(), invitedEvent.getConnection(), DateTime.now(DateTimeZone.UTC));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerInvitedNotification that = (PlayerInvitedNotification) o;

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
