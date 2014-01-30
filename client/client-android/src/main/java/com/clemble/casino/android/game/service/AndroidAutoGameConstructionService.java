package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_CONSTRUCTION_AUTO;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.AutomaticGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.service.AutoGameConstructionService;

public class AndroidAutoGameConstructionService<T extends GameState> extends AbstractClembleCasinoOperations implements AutoGameConstructionService {

    final private RestTemplate restTemplate;

    public AndroidAutoGameConstructionService(RestTemplate restTemplate, ServerRegistry apiBase) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConstruction construct(AutomaticGameRequest gameRequest) {
        return restTemplate.postForObject(buildUriWith(GAME_CONSTRUCTION_AUTO), gameRequest, GameConstruction.class);
    }

}
