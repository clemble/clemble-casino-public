package com.clemble.casino.game.service;

import java.util.Collection;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.AvailabilityGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest>{

    public GameConstruction reply(final InvitationResponseEvent gameRequest);

    public PlayerAwareEvent getReply(final Game game, final String session, final String player);

    public GameConstruction getConstruction(final Game game, final String session);

    public Collection<GameInitiation> getPending(String player);

}
