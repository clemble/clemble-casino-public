package com.clemble.casino.android.game.service;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.configuration.GameSpecificationOptions;
import com.clemble.casino.game.service.GameSpecificationService;
import static com.clemble.casino.web.game.GameWebMapping.*;

public class AndroidGameSpecificationService extends AbstractClembleCasinoOperations implements GameSpecificationService {

    final private RestTemplate restTemplate;

    public AndroidGameSpecificationService(RestTemplate restTemplate, ServerRegistry serverRegistry) {
        super(serverRegistry);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameSpecificationOptions getSpecificationOptions(String player, Game game) {
        return restTemplate.getForObject(buildUriWith(GAME_SPECIFICATION_OPTIONS, game), GameSpecificationOptions.class);
    }

}
