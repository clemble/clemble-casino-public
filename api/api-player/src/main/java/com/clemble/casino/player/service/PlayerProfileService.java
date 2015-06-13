package com.clemble.casino.player.service;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.PlayerService;
import com.clemble.casino.player.PlayerProfile;

public interface PlayerProfileService extends PlayerService {

    PlayerProfile getProfile(String player);

    List<PlayerProfile> getProfiles(Collection<String> players);

    PlayerProfile myProfile();

    PlayerProfile updateProfile(PlayerProfile playerProfile);

    List<PlayerProfile> getProfiles(String... players);

}
