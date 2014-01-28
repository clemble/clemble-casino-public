package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_RESPONSES;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_RESPONSES_PLAYER;
import static com.clemble.casino.web.game.GameWebMapping.GAME_SESSIONS;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.PlayerGameConstructionRequest;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.game.service.GameConstructionService;

public class AndroidGameConstructionService<T extends GameState> extends AbstractClembleCasinoOperations implements GameConstructionService {

    final private RestTemplate restTemplate;

    public AndroidGameConstructionService(RestTemplate restTemplate, ServerRegistry apiBase) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConstruction automatch(PlayerGameConstructionRequest gameRequest) {
        return restTemplate.postForObject(buildUriWith(GAME_SESSIONS), gameRequest, GameConstruction.class);
    }

    @Override
    public GameConstruction getConstruction(Game game, String session) {
        return restTemplate.getForObject(buildUriWith(GAME_CONSTRUCTION, game, session), GameConstruction.class);
    }

    @Override
    public PlayerAwareEvent getResponce(Game game, String session, String player) {
        return restTemplate.getForObject(buildUriWith(GAME_CONSTRUCTION_RESPONSES_PLAYER, game, session, player), PlayerAwareEvent.class);
    }

    @Override
    public GameConstruction reply(Game game, String session, InvitationResponseEvent gameRequest) {
        return restTemplate.postForObject(buildUriWith(GAME_CONSTRUCTION_RESPONSES, game, session), gameRequest, GameConstruction.class);
    }

}
