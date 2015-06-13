package com.clemble.casino.lifecycle.management.event.action;

import com.clemble.casino.KeyAware;
import com.clemble.casino.event.Event;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 10/8/14.
 */
@JsonTypeName(PlayerAction.JSON_TYPE)
public class PlayerAction<T extends Action> implements Event, PlayerAware, KeyAware {

    final public static String JSON_TYPE = "player:action";

    final private String key;
    final private String player;
    final private T action;

    @JsonCreator
    public PlayerAction(@JsonProperty("key") String key, @JsonProperty("player") String player, @JsonProperty("action") T action) {
        this.key = key;
        this.player = player;
        this.action = action;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getKey() {
        return key;
    }

    public T getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerAction)) return false;

        PlayerAction that = (PlayerAction) o;

        if (!action.equals(that.action)) return false;
        if (!key.equals(that.key)) return false;
        return player.equals(that.player);

    }

    public String toString() {
        return key + ":" + player + " > " + action;
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + action.hashCode();
        return result;
    }

}
