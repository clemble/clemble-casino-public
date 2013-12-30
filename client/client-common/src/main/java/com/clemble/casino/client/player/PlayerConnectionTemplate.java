package com.clemble.casino.client.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.List;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.service.PlayerConnectionService;

public class PlayerConnectionTemplate implements PlayerConnectionOperations, PlayerAware {

    /**
     * Generated 30/12/13
     */
    private static final long serialVersionUID = 4966541707719576636L;

    final private String player;
    final private PlayerConnectionService connectionService;
    final private PlayerProfileOperations profileOperations;

    public PlayerConnectionTemplate(String player, PlayerConnectionService connectionService, PlayerProfileOperations profileOperations) {
        this.player = checkNotNull(player);
        this.connectionService = checkNotNull(connectionService);
        this.profileOperations = checkNotNull(profileOperations);
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public List<String> getConnectionIds() {
        return getConnectionIds(player);
    }

    @Override
    public List<String> getConnectionIds(String player) {
        return connectionService.getConnections(player);
    }

    @Override
    public List<PlayerProfile> getConnections() {
        return getConnections(player);
    }

    @Override
    public List<PlayerProfile> getConnections(String player) {
        // Step 1. Fetching player ids
        List<String> players = getConnectionIds(player);
        // Step 2. Fetchinf player profiles
        return profileOperations.getPlayerProfile(players);
    }
}
