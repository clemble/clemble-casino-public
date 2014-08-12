package com.clemble.casino.player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.social.connect.ConnectionKey;

import java.util.Set;

public class PlayerConnections implements PlayerAware {

    /**
     * Generated 18/12/13
     */
    private static final long serialVersionUID = -1819811612556136513L;

    @Id
    final private String player;
    final private Set<ConnectionKey> owned;
    final private Set<ConnectionKey> connected;

    @JsonCreator
    public PlayerConnections(@JsonProperty("player") String player, @JsonProperty("owned") Set<ConnectionKey> owned, @JsonProperty("connected") Set<ConnectionKey> connected) {
        this.player = player;
        this.owned = owned;
        this.connected = connected;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Set<ConnectionKey> getOwned(){
        return owned;
    }

    public Set<ConnectionKey> getConnected() {
        return connected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerConnections that = (PlayerConnections) o;

        if (!connected.equals(that.connected)) return false;
        if (!owned.equals(that.owned)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + owned.hashCode();
        result = 31 * result + connected.hashCode();
        return result;
    }
}
