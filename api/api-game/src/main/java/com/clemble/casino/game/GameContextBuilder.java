package com.clemble.casino.game;

import java.io.Serializable;
import java.util.List;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.iterator.GamePlayerIterator;
import com.clemble.casino.game.iterator.GamePlayerIteratorFactory;
import com.clemble.casino.game.specification.MatchGameConfiguration;
import com.clemble.casino.player.PlayerAwareUtils;

public class GameContextBuilder implements Serializable {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = 7026521242574488489L;

    private List<GamePlayerContext> playerContexts;
    private GamePlayerIterator playerIterator;
    private ActionLatch actionLatch;

    public GameContextBuilder(GameInitiation initiation, MatchGameConfiguration specification) {
        this.playerContexts = GamePlayerContext.construct(initiation, specification);
        this.playerIterator = GamePlayerIteratorFactory.create(initiation);
        this.actionLatch = new ActionLatch();
    }

    public GamePlayerIterator getPlayerIterator() {
        return playerIterator;
    }

    public GameContextBuilder setPlayerIterator(GamePlayerIterator playerIterator) {
        this.playerIterator = playerIterator;
        return this;
    }

    public ActionLatch getActionLatch() {
        return actionLatch;
    }

    public GameContextBuilder setActionLatch(ActionLatch actionLatch) {
        this.actionLatch = actionLatch;
        return this;
    }

    public List<GamePlayerContext> getPlayerContexts() {
        return playerContexts;
    }

    public GamePlayerContext getPlayerContext(String player) {
        return PlayerAwareUtils.fetch(player, playerContexts);
    }

    public GameContextBuilder setPlayerContexts(List<GamePlayerContext> playerContexts) {
        this.playerContexts = playerContexts;
        return this;
    }

    public MatchGameContext build() {
        return new MatchGameContext(playerContexts, playerIterator, actionLatch, null);
    }

}
