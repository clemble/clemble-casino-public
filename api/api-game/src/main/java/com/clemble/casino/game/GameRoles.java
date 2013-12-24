package com.clemble.casino.game;

import com.clemble.casino.utils.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by mavarazy on 24/12/13.
 */
public class GameRoles {

    final public Map<String, String> playerToRole;

    public GameRoles(Collection<Map.Entry<String, String>> roles) {
        this.playerToRole = CollectionUtils.immutableMap(roles);
    }

    public String getRole(String player) {
        return playerToRole.get(player);
    }

    public Collection<Map.Entry<String, String>> getRoles() {
        return playerToRole.entrySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameRoles gameRoles = (GameRoles) o;

        if (playerToRole != null ? !playerToRole.equals(gameRoles.playerToRole) : gameRoles.playerToRole != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playerToRole != null ? playerToRole.hashCode() : 0;
    }
}
