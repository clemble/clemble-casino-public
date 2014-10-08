package com.clemble.casino.android.game;

import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.initiation.service.GameInitiationService;

import java.util.Collection;

import static com.clemble.casino.game.GameWebMapping.*;

public class AndroidGameInitiationService  extends AbstractClembleCasinoOperations implements GameInitiationService {

    final private RestTemplate restTemplate;

    public AndroidGameInitiationService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameInitiation confirm(String sessionKey) {
        return restTemplate.postForObject(buildUriWith(toGameConstructionUrl(INITIATION_READY), sessionKey), null, GameInitiation.class);
    }

    @Override
    public Collection<GameInitiation> getPending() {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toGameConstructionUrl(INITIATION_PENDING)), GameInitiation[].class));
    }


}
