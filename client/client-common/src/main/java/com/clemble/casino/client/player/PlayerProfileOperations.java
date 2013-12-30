package com.clemble.casino.client.player;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.PlayerProfile;

public interface PlayerProfileOperations extends PlayerAware {

    public PlayerProfile getPlayerProfile();

    public PlayerProfile getPlayerProfile(String player);
    
    public List<PlayerProfile> getPlayerProfile(String ... players);

    public List<PlayerProfile> getPlayerProfile(Collection<String> players);

    public PlayerProfile updatePlayerProfile(PlayerProfile playerProfile);

}
