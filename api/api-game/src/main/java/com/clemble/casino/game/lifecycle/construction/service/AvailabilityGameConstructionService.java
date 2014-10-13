package com.clemble.casino.game.lifecycle.construction.service;

import com.clemble.casino.game.lifecycle.construction.AvailabilityGameRequest;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;
import com.clemble.casino.game.lifecycle.construction.event.PlayerInvitationAction;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.PlayerAware;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest> {

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(String sessionKey);

    public PlayerAction getReply(String sessionKey, String player);

    public GameConstruction reply(String sessionKey, PlayerInvitationAction gameRequest);

}
