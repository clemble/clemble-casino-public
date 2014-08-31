package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.CONSTRUCTION_AUTO;
import static com.clemble.casino.game.GameWebMapping.toGameConstructionUrl;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.AutomaticGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.service.AutoGameConstructionService;

public class AndroidAutoGameConstructionService<T extends GameState> extends AbstractClembleCasinoOperations implements AutoGameConstructionService {

    final private RestTemplate restTemplate;

    public AndroidAutoGameConstructionService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConstruction construct(AutomaticGameRequest gameRequest) {
        return restTemplate.postForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION_AUTO)), gameRequest, GameConstruction.class);
    }

}
