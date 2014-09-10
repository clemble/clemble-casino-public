package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.CONSTRUCTION_AUTO;
import static com.clemble.casino.game.GameWebMapping.CONSTRUCTION_AUTO_PENDING;
import static com.clemble.casino.game.GameWebMapping.toGameConstructionUrl;

import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construction.AutomaticGameRequest;
import com.clemble.casino.game.construction.GameConstruction;
import com.clemble.casino.game.construction.AutoGameConstructionService;

import java.util.Collection;

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

    @Override
    public Collection<GameConstruction> getPending(String player) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION_AUTO_PENDING), player), GameConstruction[].class));
    }

}
