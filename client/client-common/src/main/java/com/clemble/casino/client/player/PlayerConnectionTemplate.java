package com.clemble.casino.client.player;

import java.util.List;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.service.PlayerConnectionService;

public class PlayerConnectionTemplate implements PlayerConnectionOperations, PlayerAware {

    /**
     * Generated 30/12/13
     */
    private static final long serialVersionUID = 4966541707719576636L;

    final private String player;
    final private PlayerConnectionService connectionService;

    public PlayerConnectionTemplate(String player, PlayerConnectionService connectionService) {
        this.player = player;
        this.connectionService = connectionService;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public List<String> getConnections() {
        return getConnections(player);
    }

    @Override
    public List<String> getConnections(String player) {
        return connectionService.getConnections(player);
    }
}
