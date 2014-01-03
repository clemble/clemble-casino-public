package com.clemble.casino.client.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventListenerControllerAgregate;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.event.NotificationMapping;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.player.PlayerPresenceChangedEvent;
import com.clemble.casino.player.service.PlayerPresenceService;

public class PlayerPresenceTemplate implements PlayerPresenceOperations {

    final private String player;
    final private PlayerPresenceService playerPresenceService;
    final private EventListenerOperations listenerOperations;

    public PlayerPresenceTemplate(String player, PlayerPresenceService playerPresenceService, EventListenerOperations listenerOperations) {
        this.player = checkNotNull(player);
        this.playerPresenceService = checkNotNull(playerPresenceService);
        this.listenerOperations = checkNotNull(listenerOperations);
    }

    @Override
    public PlayerPresence getPresence() {
        return playerPresenceService.getPresence(player);
    }

    @Override
    public PlayerPresence getPresence(String player) {
        return playerPresenceService.getPresence(player);
    }

    @Override
    public List<PlayerPresence> getPresences(String... players) {
        return getPresences(Arrays.asList(players));
    }

    @Override
    public List<PlayerPresence> getPresences(List<String> players) {
        return playerPresenceService.getPresences(players);
    }

    @Override
    public EventListenerController subscribe(String player, EventListener<PlayerPresenceChangedEvent> listener) {
        if(player == null || listener == null)
            throw new IllegalArgumentException();
        return listenerOperations.subscribe(NotificationMapping.toPresenceChannel(player), listener);
    }

    @Override
    public EventListenerController subscribe(List<String> players, EventListener<PlayerPresenceChangedEvent> listener) {
        Collection<EventListenerController> listenerControllers = new ArrayList<EventListenerController>(players.size());
        for(String player: players)
            listenerControllers.add(subscribe(player, listener));
        return new EventListenerControllerAgregate(listenerControllers);
    }

}
