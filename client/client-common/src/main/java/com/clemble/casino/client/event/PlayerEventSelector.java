package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.player.PlayerAware;

/**
 * Created by mavarazy on 12/31/14.
 */
public class PlayerEventSelector implements EventSelector {

    final private String player;

    public PlayerEventSelector(String player) {
        this.player = player;
    }

    @Override
    public boolean filter(Event event) {
        return (event instanceof PlayerAware) && ((PlayerAware) event).getPlayer().equals(player);
    }
}
