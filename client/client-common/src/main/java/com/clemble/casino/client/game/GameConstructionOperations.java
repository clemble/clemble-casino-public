package com.clemble.casino.client.game;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.game.lifecycle.construction.event.PlayerInvitationAction;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.event.GameEvent;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.event.PlayerEvent;

public interface GameConstructionOperations extends GameConfigurationOperations {

    public GameConstruction getConstruct(String sessionKey);

    public PlayerAction getResponse(String sessionKey, String player);

    public GameConstruction constructAutomatch(GameConfiguration configuration);

    public GameConstruction constructAvailability(GameConfiguration configuration, String ... players);

    public GameConstruction constructAvailability(GameConfiguration configuration, Collection<String> players);

    public GameConstruction accept(String sessionKey);

    public GameConstruction decline(String sessionKey);

    public GameConstruction reply(String sessionKey, PlayerInvitationAction gameRequest);

    public Collection<GameConstruction> pending();

    public GameInitiation confirm(String sessionKey);

    public EventListenerController watch(String sessionKey, EventListener<GameEvent> constructionListener);

}
