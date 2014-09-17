package com.clemble.casino.game.construction.service;

import com.clemble.casino.game.construction.AvailabilityGameRequest;
import com.clemble.casino.game.construction.GameConstruction;
import com.clemble.casino.game.construction.event.GameInvitationResponseEvent;
import com.clemble.casino.player.event.PlayerEvent;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest> {

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(String sessionKey);

    public PlayerEvent getReply(String sessionKey, String player);

    public GameConstruction reply(GameInvitationResponseEvent gameRequest);

}
