package com.clemble.casino.android.game.service;

import static com.clemble.casino.web.game.GameWebMapping.GAME_CONFIGURATIONS_POT;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONFIGURATIONS_MATCH;
import static com.clemble.casino.web.game.GameWebMapping.GAME_CONFIGURATIONS_TOURNAMENT;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.service.GameConfigurationService;
import com.clemble.casino.game.specification.MatchGameConfiguration;
import com.clemble.casino.game.specification.PotGameConfiguration;
import com.clemble.casino.game.specification.TournamentGameConfiguration;
import com.clemble.casino.utils.CollectionUtils;

public class AndroidGameConfigurationService extends AbstractClembleCasinoOperations implements GameConfigurationService {

    final private RestTemplate restTemplate;

    public AndroidGameConfigurationService(RestTemplate restTemplate, ServerRegistry serverRegistry) {
        super(serverRegistry);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MatchGameConfiguration> getMatchConfigurations() {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUri(GAME_CONFIGURATIONS_MATCH), MatchGameConfiguration[].class));
    }

    @Override
    public List<PotGameConfiguration> getPotConfigurations() {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUri(GAME_CONFIGURATIONS_POT), PotGameConfiguration[].class));
    }

    @Override
    public List<TournamentGameConfiguration> getTournamentConfigurations() {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUri(GAME_CONFIGURATIONS_TOURNAMENT), TournamentGameConfiguration[].class));
    }

}
