package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.INITIATION_PENDING;
import static com.clemble.casino.game.GameWebMapping.INITIATION_READY;
import static com.clemble.casino.game.GameWebMapping.toGameUrl;

import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.game.construction.service.GameInitiationService;

import java.util.Collection;

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

    @Override
    public Collection<GameInitiation> getPending() {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toGameUrl(INITIATION_PENDING)), GameInitiation[].class));
    }


}
