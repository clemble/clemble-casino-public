package com.clemble.casino.player.service;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.web.mapping.WebMapping;
import com.clemble.casino.web.player.PlayerWebMapping;

public interface PlayerProfileService extends ClembleService {

    public PlayerProfile getPlayerProfile(String playerId);

    public PlayerProfile updatePlayerProfile(String player, PlayerProfile playerProfile);

    public List<PlayerProfile> getPlayerProfile(Collection<String> players);

}
