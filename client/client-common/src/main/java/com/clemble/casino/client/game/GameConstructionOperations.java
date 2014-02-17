package com.clemble.casino.client.game;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameAware;
import com.clemble.casino.game.GameSessionAwareEvent;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.game.specification.GameConfiguration;

public interface GameConstructionOperations<T extends GameState> extends GameConfigurationOperations, GameAware {

    public GameConstruction getConstruct(final String session);

    public PlayerAwareEvent getResponce(final String session, final String player);

    public GameConstruction constructAutomatch(final GameConfiguration specification);

    public GameConstruction constructAvailability(final GameConfiguration specification, String ... players);

    public GameConstruction constructAvailability(final GameConfiguration specification, Collection<String> players);

    public GameConstruction accept(final String session);

    public GameConstruction decline(final String session);

    public GameConstruction reply(final String session, final InvitationResponseEvent gameRequest);

    public Collection<GameInitiation> pending();

    public GameInitiation confirm(final String session);

    public GameActionOperations<T> getActionOperations(String session);

    public EventListenerController watch(String session, EventListener<GameSessionAwareEvent> constructionListener);

}
