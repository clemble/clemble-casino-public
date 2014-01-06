package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_INITIATION_READY;
import static com.clemble.casino.web.game.GameWebMapping.GAME_INITIATION;

import java.util.Collection;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.service.GameInitiationService;
import com.clemble.casino.utils.CollectionUtils;

public class AndroidGameInitiationService  extends AbstractClembleCasinoOperations implements GameInitiationService {

    final private RestTemplate restTemplate;

    public AndroidGameInitiationService(RestTemplate restTemplate, ServerRegistry apiBase) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<GameInitiation> pending(Game game, String player) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(GAME_INITIATION, game, player), GameInitiation[].class));
    }

    @Override
    public GameInitiation ready(Game game, String session, String player) {
        return restTemplate.postForObject(buildUriWith(GAME_INITIATION_READY, game, session, player), null, GameInitiation.class);
    }


}
