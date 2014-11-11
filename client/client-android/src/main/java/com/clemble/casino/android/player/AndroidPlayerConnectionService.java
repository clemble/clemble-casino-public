package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.player.PlayerConnectionWebMapping.*;

import java.net.URI;
import java.util.Set;

import com.clemble.casino.player.service.PlayerConnectionService;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.utils.CollectionUtils;

public class AndroidPlayerConnectionService extends AbstractClembleCasinoOperations implements PlayerConnectionService {

    final private RestTemplate restTemplate;

    public AndroidPlayerConnectionService(RestTemplate restClientService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public Set<String> myConnections() {
        // Step 1. Fetching player connections
        URI playerUri = buildUriWith(toConnectionUrl(MY_CONNECTED_CONNECTIONS));
        // Step 3. Requesting through RestTemplate
        return CollectionUtils.immutableSet(restTemplate.getForObject(playerUri, String[].class));
    }

    @Override
    public Set<String> getConnections(String player) {
        // Step 1. Fetching player connections
        URI playerUri = buildUriWith(toConnectionUrl(PLAYER_CONNECTION_CONNECTIONS), player);
        // Step 3. Requesting through RestTemplate
        return CollectionUtils.immutableSet(restTemplate.getForObject(playerUri, String[].class));
    }

}
