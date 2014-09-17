package com.clemble.casino.client.event;

import com.clemble.casino.game.event.GameEvent;
import com.clemble.casino.player.PlayerAware;

public class GamePlayerToMoveEvent implements GameEvent, PlayerAware {

    /**
     * Generated 28/12/13
     */
    private static final long serialVersionUID = 1551787087014740990L;

    final private String player;
    final private String sessionKey;
    final private String expectedMove;
    final private boolean isMyTurn;

    public GamePlayerToMoveEvent(String sessionKey, String player, String expectedMove, boolean isMyTurn) {
        this.player = player;
        this.sessionKey = sessionKey;
        this.expectedMove = expectedMove;
        this.isMyTurn = isMyTurn;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getExpectedMove() {
        return expectedMove;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    @Override
    public String toString(){
        return "toMove:" + player + ":" + sessionKey + ":" + (expectedMove != null ? expectedMove : "unknown");
    }

}
