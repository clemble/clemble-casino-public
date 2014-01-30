package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_CONFIGURATIONS;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.service.GameConfigurationService;
import com.clemble.casino.game.specification.GameConfigurations;

public class AndroidGameConfigurationService extends AbstractClembleCasinoOperations implements GameConfigurationService {

    final private RestTemplate restTemplate;

    public AndroidGameConfigurationService(RestTemplate restTemplate, ServerRegistry serverRegistry) {
        super(serverRegistry);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConfigurations getConfigurations() {
        return restTemplate.getForObject(buildUri(GAME_CONFIGURATIONS), GameConfigurations.class);
    }

}
