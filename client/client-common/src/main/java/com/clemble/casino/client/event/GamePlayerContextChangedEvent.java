package com.clemble.casino.client.event;

import com.clemble.casino.game.MatchGamePlayerContext;
import com.clemble.casino.player.PlayerAware;

public class GamePlayerContextChangedEvent implements GameUnitEvent, PlayerAware {

    /**
     * Generated 28/12/13
     */
    private static final long serialVersionUID = 3971951397259680180L;

    final private String player;
    final private MatchGamePlayerContext context;
    final private MatchGamePlayerContext oldContext;

    public GamePlayerContextChangedEvent(String player, MatchGamePlayerContext context, MatchGamePlayerContext oldContext) {
        this.player = player;
        this.context = context;
        this.oldContext = oldContext;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public MatchGamePlayerContext getContext() {
        return context;
    }

    public MatchGamePlayerContext getOldContext() {
        return oldContext;
    }

}
