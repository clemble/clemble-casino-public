package com.clemble.casino.game;

import java.io.Serializable;
import java.util.List;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.iterator.GamePlayerIterator;
import com.clemble.casino.game.iterator.GamePlayerIteratorFactory;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 24/12/13.
 */
public class GameContext implements Serializable {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = 7026521242574488489L;

    final private List<GamePlayerContext> playerContexts;
    final private GamePlayerIterator playerIterator;
    final private ActionLatch actionLatch;

    @JsonCreator
    public GameContext(@JsonProperty("clock") List<GamePlayerContext> playerContexts, @JsonProperty("playerIterator") GamePlayerIterator playerIterator,
            @JsonProperty("actionLatch") ActionLatch actionLatch) {
        this.playerContexts = playerContexts;
        this.playerIterator = playerIterator;
        this.actionLatch = actionLatch;
    }

    public GameContext(GameInitiation initiation) {
        this.playerContexts = GamePlayerContext.construct(initiation);
        this.playerIterator = GamePlayerIteratorFactory.create(initiation);
        this.actionLatch = new ActionLatch();
    }

    public GamePlayerIterator getPlayerIterator() {
        return playerIterator;
    }

    public ActionLatch getActionLatch() {
        return actionLatch;
    }

    public List<GamePlayerContext> getPlayerContexts() {
        return playerContexts;
    }

    public GamePlayerContext getPlayerContext(String player) {
        return PlayerAwareUtils.fetch(player, playerContexts);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionLatch == null) ? 0 : actionLatch.hashCode());
        result = prime * result + ((playerContexts == null) ? 0 : playerContexts.hashCode());
        result = prime * result + ((playerIterator == null) ? 0 : playerIterator.hashCode());
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
        GameContext other = (GameContext) obj;
        if (actionLatch == null) {
            if (other.actionLatch != null)
                return false;
        } else if (!actionLatch.equals(other.actionLatch))
            return false;
        if (playerContexts == null) {
            if (other.playerContexts != null)
                return false;
        } else if (!playerContexts.equals(other.playerContexts))
            return false;
        if (playerIterator == null) {
            if (other.playerIterator != null)
                return false;
        } else if (!playerIterator.equals(other.playerIterator))
            return false;
        return true;
    }
}
