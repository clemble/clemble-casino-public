package com.clemble.casino.player.service;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.PlayerProfile;

public interface PlayerProfileService extends PlayerProfileServiceContract {

    public PlayerProfile myProfile();

    public PlayerProfile updateProfile(PlayerProfile playerProfile);

    public List<PlayerProfile> getProfiles(String... players);

}
