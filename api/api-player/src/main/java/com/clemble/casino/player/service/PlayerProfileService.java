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

    @RequestMapping(method = RequestMethod.GET, value = PlayerWebMapping.PLAYER_PROFILE, produces = WebMapping.PRODUCES)
    public @ResponseBody PlayerProfile getPlayerProfile(@PathVariable("playerId") String playerId);

    @RequestMapping(method = RequestMethod.POST, value = PlayerWebMapping.PLAYER_PROFILE, produces = WebMapping.PRODUCES)
    public @ResponseBody PlayerProfile updatePlayerProfile(@PathVariable("playerId") String player, @RequestBody PlayerProfile playerProfile);

    @RequestMapping(method = RequestMethod.GET, value = PlayerWebMapping.PLAYER_PROFILES, produces = WebMapping.PRODUCES)
    public @ResponseBody List<PlayerProfile> getPlayerProfile(@RequestParam("player") Collection<String> players);

}
