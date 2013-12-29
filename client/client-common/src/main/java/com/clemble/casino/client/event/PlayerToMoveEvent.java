package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.PlayerAware;

public class PlayerToMoveEvent implements GameSessionAware, PlayerAware, Event {

    /**
     * Generated 28/12/13
     */
    private static final long serialVersionUID = 1551787087014740990L;

    final private String player;
    final private GameSessionKey sessionKey;
    final private Class<?> expectedMove;
    final private boolean isMyTurn;

    public PlayerToMoveEvent(GameSessionKey sessionKey, String player, Class<?> expectedMove, boolean isMyTurn) {
        this.player = player;
        this.sessionKey = sessionKey;
        this.expectedMove = expectedMove;
        this.isMyTurn = isMyTurn;
    }

    @Override
    public GameSessionKey getSession() {
        return sessionKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Class<?> getExpectedMove() {
        return expectedMove;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

}
