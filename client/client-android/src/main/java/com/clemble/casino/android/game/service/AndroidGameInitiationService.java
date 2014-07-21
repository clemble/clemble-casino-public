package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.INITIATION_READY;
import static com.clemble.casino.web.game.GameWebMapping.toGameUrl;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.service.GameInitiationService;

public class AndroidGameInitiationService  extends AbstractClembleCasinoOperations implements GameInitiationService {

    final private RestTemplate restTemplate;

    public AndroidGameInitiationService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameInitiation confirm(Game game, String session, String player) {
        return restTemplate.postForObject(buildUriWith(toGameUrl(INITIATION_READY), game, session, player), null, GameInitiation.class);
    }


}
