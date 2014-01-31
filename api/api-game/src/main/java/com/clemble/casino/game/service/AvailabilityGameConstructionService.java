package com.clemble.casino.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_AVAILABILITY;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_AVAILABILITY_PENDING;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_RESPONSES;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_RESPONSES_PLAYER;

import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.AvailabilityGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.web.mapping.WebMapping;

public interface AvailabilityGameConstructionService extends GameConstructionService<AvailabilityGameRequest>{

    @Override
    @RequestMapping(method = RequestMethod.POST, value = GAME_CONSTRUCTION_AVAILABILITY, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody GameConstruction construct(@RequestBody AvailabilityGameRequest gameRequest);

    @RequestMapping(method = RequestMethod.GET, value = GAME_CONSTRUCTION, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody GameConstruction getConstruction(@PathVariable("game") Game game, @PathVariable("session") String session);

    @RequestMapping(method = RequestMethod.GET, value = GAME_CONSTRUCTION_RESPONSES_PLAYER, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody PlayerAwareEvent getReply(@PathVariable("game") Game game, @PathVariable("session") String session, @PathVariable("playerId") String player);

    @RequestMapping(method = RequestMethod.POST, value = GAME_CONSTRUCTION_RESPONSES, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody GameConstruction reply(@RequestBody InvitationResponseEvent gameRequest);

    @RequestMapping(method = RequestMethod.GET, value = GAME_CONSTRUCTION_AVAILABILITY_PENDING, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Collection<GameInitiation> getPending(@PathVariable("playerId") String player);

}
