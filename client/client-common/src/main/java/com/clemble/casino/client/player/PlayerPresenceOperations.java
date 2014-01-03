package com.clemble.casino.client.player;

import java.util.List;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.player.PlayerPresenceChangedEvent;

public interface PlayerPresenceOperations {

    public PlayerPresence getPresence();

    public PlayerPresence getPresence(String player);

    public List<PlayerPresence> getPresences(String ... players);

    public List<PlayerPresence> getPresences(List<String> players);

    public EventListenerController subscribe(String player, EventListener<PlayerPresenceChangedEvent> listener);

    public EventListenerController subscribe(List<String> player, EventListener<PlayerPresenceChangedEvent> listener);

}
