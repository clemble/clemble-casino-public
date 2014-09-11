package com.clemble.casino.game.construction.service;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.construction.AvailabilityGameRequest;
import com.clemble.casino.game.construction.GameConstruction;
import com.clemble.casino.game.construction.event.InvitationResponseEvent;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest> {

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(String sessionKey);

    public PlayerAwareEvent getReply(String sessionKey, String player);

    public GameConstruction reply(InvitationResponseEvent gameRequest);

}
