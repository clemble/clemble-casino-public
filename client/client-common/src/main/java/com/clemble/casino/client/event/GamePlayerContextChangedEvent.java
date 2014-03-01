package com.clemble.casino.client.event;

import com.clemble.casino.game.RoundGamePlayerContext;
import com.clemble.casino.player.PlayerAware;

public class GamePlayerContextChangedEvent implements GameUnitEvent, PlayerAware {

    /**
     * Generated 28/12/13
     */
    private static final long serialVersionUID = 3971951397259680180L;

    final private String player;
    final private RoundGamePlayerContext context;
    final private RoundGamePlayerContext oldContext;

    public GamePlayerContextChangedEvent(String player, RoundGamePlayerContext context, RoundGamePlayerContext oldContext) {
        this.player = player;
        this.context = context;
        this.oldContext = oldContext;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public RoundGamePlayerContext getContext() {
        return context;
    }

    public RoundGamePlayerContext getOldContext() {
        return oldContext;
    }

}
