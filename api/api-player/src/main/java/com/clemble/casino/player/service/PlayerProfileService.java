package com.clemble.casino.player.service;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.player.PlayerProfile;

public interface PlayerProfileService {

    public PlayerProfile getPlayerProfile(String player);

    public List<PlayerProfile> getPlayerProfile(Collection<String> players);

    public PlayerProfile updatePlayerProfile(String player, PlayerProfile playerProfile);

}
