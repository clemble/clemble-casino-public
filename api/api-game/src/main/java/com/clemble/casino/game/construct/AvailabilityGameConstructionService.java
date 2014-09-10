package com.clemble.casino.game.construct;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest> {

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(String sessionKey);

    public PlayerAwareEvent getReply(String sessionKey, String player);

    public GameConstruction reply(InvitationResponseEvent gameRequest);

}
