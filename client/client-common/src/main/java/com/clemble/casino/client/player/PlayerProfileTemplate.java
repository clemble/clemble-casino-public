package com.clemble.casino.client.player;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.service.PlayerProfileService;

public class PlayerProfileTemplate implements PlayerProfileOperations {

    /**
     * Generated 15/12/13
     */
    private static final long serialVersionUID = 2044631083380608080L;

    final private String player;
    final private PlayerProfileService playerProfileService;

    public PlayerProfileTemplate(String player, PlayerProfileService playerProfileService) {
        this.player = player;
        this.playerProfileService = playerProfileService;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return playerProfileService.getPlayerProfile(player);
    }

    @Override
    public PlayerProfile getPlayerProfile(String player) {
        return playerProfileService.getPlayerProfile(player);
    }

    @Override
    public List<PlayerProfile> getPlayerProfile(String... players) {
        return getPlayerProfile(Arrays.asList(players));
    }

    @Override
    public List<PlayerProfile> getPlayerProfile(Collection<String> players) {
        return playerProfileService.getPlayerProfile(players);
    }

    @Override
    public PlayerProfile updatePlayerProfile(PlayerProfile playerProfile) {
        playerProfile.setPlayer(player);
        return playerProfileService.updatePlayerProfile(player, playerProfile);
    }

}
