package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.CONFIGURATION;
import static com.clemble.casino.game.GameWebMapping.toGameUrl;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.service.GameConfigurationService;
import com.clemble.casino.game.specification.GameConfigurations;

public class AndroidGameConfigurationService extends AbstractClembleCasinoOperations implements GameConfigurationService {

    final private RestTemplate restTemplate;

    public AndroidGameConfigurationService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConfigurations getConfigurations() {
        return restTemplate.getForObject(buildUri(toGameUrl(CONFIGURATION)), GameConfigurations.class);
    }

}
