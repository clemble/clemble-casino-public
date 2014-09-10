package com.clemble.casino.game.construction;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.construction.event.InvitationResponseEvent;

import java.util.List;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest> {

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(String sessionKey);

    public PlayerAwareEvent getReply(String sessionKey, String player);

    public GameConstruction reply(InvitationResponseEvent gameRequest);

}
