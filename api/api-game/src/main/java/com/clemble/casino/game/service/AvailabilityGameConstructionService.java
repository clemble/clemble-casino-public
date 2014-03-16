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
    public GameConstruction construct( AvailabilityGameRequest gameRequest);

    public GameConstruction getConstruction(Game game, String session);

    public PlayerAwareEvent getReply(Game game, String session, String player);

    public GameConstruction reply(InvitationResponseEvent gameRequest);

    public Collection<GameInitiation> getPending(String player);

}
