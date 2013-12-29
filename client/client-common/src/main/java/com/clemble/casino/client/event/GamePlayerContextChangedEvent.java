package com.clemble.casino.client.event;

import com.clemble.casino.game.GamePlayerContext;
import com.clemble.casino.player.PlayerAware;

public class GamePlayerContextChangedEvent implements GameUnitEvent, PlayerAware {

    /**
     * Generated 28/12/13
     */
    private static final long serialVersionUID = 3971951397259680180L;

    final private String player;
    final private GamePlayerContext context;
    final private GamePlayerContext oldContext;

    public GamePlayerContextChangedEvent(String player, GamePlayerContext context, GamePlayerContext oldContext) {
        this.player = player;
        this.context = context;
        this.oldContext = oldContext;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public GamePlayerContext getContext() {
        return context;
    }

    public GamePlayerContext getOldContext() {
        return oldContext;
    }

}
