package com.clemble.casino.game.service;

import java.util.Collection;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.game.construct.AvailabilityGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest>{

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(String sessionKey);

    public PlayerAwareEvent getReply(String sessionKey, String player);

    public GameConstruction reply(InvitationResponseEvent gameRequest);

}
