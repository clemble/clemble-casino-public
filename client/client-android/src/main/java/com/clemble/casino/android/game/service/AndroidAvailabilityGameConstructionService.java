package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_RESPONSES;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_RESPONSES_PLAYER;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_AVAILABILITY_PENDING;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_AVAILABILITY;

import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.AvailabilityGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.game.service.AvailabilityGameConstructionService;
import com.clemble.casino.utils.CollectionUtils;

public class AndroidAvailabilityGameConstructionService<T extends GameState> extends AbstractClembleCasinoOperations implements AvailabilityGameConstructionService {

    final private RestTemplate restTemplate;

    public AndroidAvailabilityGameConstructionService(RestTemplate restTemplate, ServerRegistry apiBase) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest) {
        return restTemplate.postForObject(buildUriWith(GAME_CONSTRUCTION_AVAILABILITY), gameRequest, GameConstruction.class);
    }

    @Override
    public GameConstruction getConstruction(Game game, String session) {
        return restTemplate.getForObject(buildUriWith(GAME_CONSTRUCTION, game, session), GameConstruction.class);
    }

    @Override
    public PlayerAwareEvent getReply(Game game, String session, String player) {
        return restTemplate.getForObject(buildUriWith(GAME_CONSTRUCTION_RESPONSES_PLAYER, game, session, player), PlayerAwareEvent.class);
    }

    @Override
    public GameConstruction reply(InvitationResponseEvent request) {
        return restTemplate.postForObject(buildUriWith(GAME_CONSTRUCTION_RESPONSES), request, GameConstruction.class);
    }

    @Override
    public Collection<GameInitiation> getPending(String player) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(GAME_CONSTRUCTION_AVAILABILITY_PENDING, player), GameInitiation[].class));
    }

}
