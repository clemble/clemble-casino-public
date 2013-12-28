package com.clemble.casino.game;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 21/12/13.
 */
public class GamePlayerRole implements PlayerAware, GameRoleAware {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = -2977977479889713389L;

    final private String player;
    final private String role;

    @JsonCreator
    public GamePlayerRole(@JsonProperty("player") String player, @JsonProperty("role") String role) {
        this.player = player;
        this.role = role;
    }

    public String getPlayer() {
        return player;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GamePlayerRole that = (GamePlayerRole) o;

        if (player != null ? !player.equals(that.player) : that.player != null)
            return false;
        if (role != null ? !role.equals(that.role) : that.role != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return player + ':' + role;
    }
}
