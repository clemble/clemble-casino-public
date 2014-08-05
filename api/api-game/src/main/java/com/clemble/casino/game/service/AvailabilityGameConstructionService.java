package com.clemble.casino.game.service;

import java.util.Collection;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.AvailabilityGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest>{

    @Override
    public GameConstruction construct( AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(Game game, String session);

    public PlayerAwareEvent getReply(Game game, String session, String player);

    public GameConstruction reply(InvitationResponseEvent gameRequest);

    public Collection<GameInitiation> getPending(String player);

}
