package com.clemble.casino.client.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.player.PlayerSession;
import com.clemble.casino.player.service.PlayerSessionService;

public class PlayerSessionTemplate implements PlayerSessionOperations {

    final private String player;
    final private PlayerSessionService playerSessionService;

    public PlayerSessionTemplate(String player, PlayerSessionService playerSessionService) {
        this.player = player;
        this.playerSessionService = checkNotNull(playerSessionService);
    }

    @Override
    public PlayerSession create() {
        return playerSessionService.create(player);
    }

    @Override
    public PlayerSession refreshPlayerSession(String sessionId) {
        return playerSessionService.refreshPlayerSession(player, sessionId);
    }

    @Override
    public void endPlayerSession(String sessionId) {
        playerSessionService.endPlayerSession(player, sessionId);
    }

    @Override
    public PlayerSession getPlayerSession(String sessionId) {
        return playerSessionService.getPlayerSession(player, sessionId);
    }

}
