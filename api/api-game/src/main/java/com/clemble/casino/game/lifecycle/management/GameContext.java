package com.clemble.casino.game.lifecycle.management;

import java.io.Serializable;
import java.util.List;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.lifecycle.management.StateContext;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
abstract public class GameContext<T extends GamePlayerContext> implements Serializable, GameSessionAware, StateContext<T> {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = -8929596035884162377L;

    final private String sessionKey;
    final private GameContext<?> parent;
    final private List<T> playerContexts;

    public GameContext(String sessionKey, GameContext<?> parent, List<T> playerContexts) {
        this.parent = parent;
        this.sessionKey = sessionKey;
        this.playerContexts = playerContexts;
    }

    public GameContext<?> getParent() {
        return parent;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public List<T> getPlayerContexts() {
        return playerContexts;
    }

    @Override
    public T getPlayerContext(String player) {
        return PlayerAwareUtils.filter(player, playerContexts);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((parent == null) ? 0 : parent.hashCode());
        result = prime * result + ((playerContexts == null) ? 0 : playerContexts.hashCode());
        result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameContext<?> other = (GameContext<?>) obj;
        if (parent == null) {
            if (other.parent != null)
                return false;
        } else if (!parent.equals(other.parent))
            return false;
        if (playerContexts == null) {
            if (other.playerContexts != null)
                return false;
        } else if (!playerContexts.equals(other.playerContexts))
            return false;
        if (sessionKey == null) {
            if (other.sessionKey != null)
                return false;
        } else if (!sessionKey.equals(other.sessionKey))
            return false;
        return true;
    }

}
