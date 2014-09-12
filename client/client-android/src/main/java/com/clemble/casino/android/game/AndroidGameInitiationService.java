package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.INITIATION_READY;
import static com.clemble.casino.game.GameWebMapping.toGameUrl;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.game.construction.service.GameInitiationService;

public class AndroidGameInitiationService  extends AbstractClembleCasinoOperations implements GameInitiationService {

    final private RestTemplate restTemplate;

    public AndroidGameInitiationService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameInitiation confirm(String sessionKey) {
        return restTemplate.postForObject(buildUriWith(toGameUrl(INITIATION_READY), sessionKey), null, GameInitiation.class);
    }


}
