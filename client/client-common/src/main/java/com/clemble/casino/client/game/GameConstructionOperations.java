package com.clemble.casino.client.game;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameAware;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.PlayerGameConstructionRequest;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.game.specification.GameSpecification;

public interface GameConstructionOperations<T extends GameState> extends GameSpecificationOperations, GameAware {

    public GameConstruction getConstruct(final String session);

    public PlayerAwareEvent getResponce(final String session, final String player);

    public GameConstruction construct(final PlayerGameConstructionRequest gameRequest);

    public GameConstruction constructAutomatch(final GameSpecification specification);

    public GameConstruction constructAvailability(final GameSpecification specification, String ... players);

    public GameConstruction constructAvailability(final GameSpecification specification, Collection<String> players);

    public GameConstruction accept(final String session);

    public GameConstruction decline(final String session);

    public GameConstruction response(final String session, final InvitationResponseEvent gameRequest);

    public GameActionOperations<T> getActionOperations(String session);

    public EventListenerController watch(String session, EventListener constructionListener);

}
