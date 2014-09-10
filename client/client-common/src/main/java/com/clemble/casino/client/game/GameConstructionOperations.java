package com.clemble.casino.client.game;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.event.GameSessionAwareEvent;
import com.clemble.casino.game.construction.GameConstruction;
import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.game.construction.event.InvitationResponseEvent;
import com.clemble.casino.game.configuration.GameConfiguration;

public interface GameConstructionOperations extends GameConfigurationOperations {

    public GameConstruction getConstruct(String sessionKey);

    public PlayerAwareEvent getResponce(String sessionKey, String player);

    public GameConstruction constructAutomatch(GameConfiguration configuration);

    public GameConstruction constructAvailability(GameConfiguration configuration, String ... players);

    public GameConstruction constructAvailability(GameConfiguration configuration, Collection<String> players);

    public GameConstruction accept(String sessionKey);

    public GameConstruction decline(String sessionKey);

    public GameConstruction reply(String sessionKey, InvitationResponseEvent gameRequest);

    public Collection<GameConstruction> pending();

    public GameInitiation confirm(String sessionKey);

    public EventListenerController watch(String sessionKey, EventListener<GameSessionAwareEvent> constructionListener);

}
