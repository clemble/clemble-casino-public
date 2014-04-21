package com.clemble.casino.player.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.web.mapping.WebMapping;
import com.clemble.casino.web.player.PlayerWebMapping;

public interface PlayerPresenceService extends ClembleService {

    @RequestMapping(value = PlayerWebMapping.PRESENCES_PLAYER, method = RequestMethod.GET, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody PlayerPresence getPresence(@PathVariable("playerId") String player);

    @RequestMapping(value = PlayerWebMapping.PRESENCES, method = RequestMethod.GET, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<PlayerPresence> getPresences(@RequestParam(required = true, value = PlayerWebMapping.PLAYER_PRESENCES_PARAM) List<String> players);

}
