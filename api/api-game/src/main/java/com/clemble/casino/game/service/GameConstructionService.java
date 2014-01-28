package com.clemble.casino.game.service;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.PlayerGameConstructionRequest;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

public interface GameConstructionService {

    public GameConstruction automatch(final PlayerGameConstructionRequest gameRequest);

    public GameConstruction getConstruction(final Game game, final String session);

    public PlayerAwareEvent getResponce(final Game game, final String session, final String player);

    public GameConstruction reply(final Game game, String sessionId, final InvitationResponseEvent gameRequest);

}
