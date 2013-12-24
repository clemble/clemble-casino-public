package com.clemble.casino.game;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.game.account.GameAccount;
import com.clemble.casino.game.account.GameAccountFactory;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.iterator.GamePlayerIterator;
import com.clemble.casino.game.iterator.GamePlayerIteratorFactory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by mavarazy on 24/12/13.
 */
public class GameContext implements Serializable {

    final private GameClock clock;
    final private GameAccount account;
    final private GamePlayerIterator playerIterator;
    final private ActionLatch actionLatch;

    @JsonCreator
    public GameContext(
            @JsonProperty("clock") GameClock clock,
            @JsonProperty("account") GameAccount account,
            @JsonProperty("playerIterator") GamePlayerIterator playerIterator,
            @JsonProperty("actionLatch") ActionLatch actionLatch) {
        this.clock = clock;
        this.account = account;
        this.playerIterator = playerIterator;
        this.actionLatch = actionLatch;
    }

    public GameContext(GameInitiation initiation) {
        this.account = GameAccountFactory.create(initiation);
        this.playerIterator = GamePlayerIteratorFactory.create(initiation);
        this.clock = new GameClock(initiation);
        this.actionLatch = new ActionLatch();
    }

    public GameClock getClock() {
        return clock;
    }

    public GameAccount getAccount() {
        return account;
    }

    public GamePlayerIterator getPlayerIterator() {
        return playerIterator;
    }

    public ActionLatch getActionLatch() {
        return actionLatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameContext that = (GameContext) o;

        if (!account.equals(that.account)) return false;
        if (!playerIterator.equals(that.playerIterator)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + playerIterator.hashCode();
        return result;
    }
}
