package com.clemble.casino.player.service;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.PlayerProfile;

public interface PlayerProfileService extends PlayerService {

    public PlayerProfile getProfile(String player);

    public List<PlayerProfile> getProfiles(Collection<String> players);

    public PlayerProfile myProfile();

    public PlayerProfile updateProfile(PlayerProfile playerProfile);

    public List<PlayerProfile> getProfiles(String... players);

}
