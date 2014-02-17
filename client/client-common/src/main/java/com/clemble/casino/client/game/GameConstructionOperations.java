package com.clemble.casino.client.game;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameSessionAwareEvent;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.game.specification.GameConfiguration;

public interface GameConstructionOperations extends GameConfigurationOperations {

    public GameConstruction getConstruct(final GameSessionKey session);

    public PlayerAwareEvent getResponce(final GameSessionKey session, final String player);

    public GameConstruction constructAutomatch(final GameConfiguration configuration);

    public GameConstruction constructAvailability(final GameConfiguration configuration, String ... players);

    public GameConstruction constructAvailability(final GameConfiguration configuration, Collection<String> players);

    public GameConstruction accept(final GameSessionKey session);

    public GameConstruction decline(final GameSessionKey session);

    public GameConstruction reply(final GameSessionKey session, final InvitationResponseEvent gameRequest);

    public Collection<GameInitiation> pending();

    public GameInitiation confirm(final GameSessionKey session);

    public EventListenerController watch(GameSessionKey session, EventListener<GameSessionAwareEvent> constructionListener);

}
