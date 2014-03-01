package com.clemble.casino.game;

import java.util.List;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.iterator.GamePlayerIterator;
import com.clemble.casino.game.iterator.GamePlayerIteratorFactory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 24/12/13.
 */
@JsonTypeName("matchContext")
public class RoundGameContext extends GameContext<RoundGamePlayerContext> {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = 7026521242574488489L;

    final private GamePlayerIterator playerIterator;
    final private ActionLatch actionLatch;

    @JsonCreator
    public RoundGameContext(@JsonProperty("session") GameSessionKey sessionKey,
                            @JsonProperty("playerContexts") List<RoundGamePlayerContext> playerContexts,
                            @JsonProperty("playerIterator") GamePlayerIterator playerIterator,
                            @JsonProperty("actionLatch") ActionLatch actionLatch,
                            @JsonProperty(value = "parent", required = false) GameContext<?> parent) {
        super(sessionKey, parent, playerContexts);
        this.playerIterator = playerIterator;
        this.actionLatch = actionLatch;
    }

    public RoundGameContext(GameInitiation initiation) {
        this(initiation, null);
    }

    public RoundGameContext(GameInitiation initiation, GameContext<?> parent) {
        super(initiation.getSession(), parent, RoundGamePlayerContext.construct(initiation));
        this.playerIterator = GamePlayerIteratorFactory.create(initiation);
        this.actionLatch = new ActionLatch();
    }

    public GamePlayerIterator getPlayerIterator() {
        return playerIterator;
    }

    public ActionLatch getActionLatch() {
        return actionLatch;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actionLatch == null) ? 0 : actionLatch.hashCode());
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
        RoundGameContext other = (RoundGameContext) obj;
        if (actionLatch == null) {
            if (other.actionLatch != null)
                return false;
        } else if (!actionLatch.equals(other.actionLatch))
            return false;
        if (playerIterator == null) {
            if (other.playerIterator != null)
                return false;
        } else if (!playerIterator.equals(other.playerIterator))
            return false;
        return true;
    }

}
