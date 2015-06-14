package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.player.PlayerConnectionWebMapping.*;

import java.net.URI;
import java.util.Set;

import com.clemble.casino.player.PlayerConnection;
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
    public Set<PlayerConnection> myConnections() {
        // Step 1. Fetching player connections
        URI playerUri = buildUri(toConnectionUrl(MY_CONNECTIONS));
        // Step 3. Requesting through RestTemplate
        return CollectionUtils.immutableSet(restTemplate.getForObject(playerUri, PlayerConnection[].class));
    }

    @Override
    public Integer myConnectionsCount() {
        // Step 1. Building my connections count URI
        URI countURI = buildUri(toConnectionUrl(MY_CONNECTIONS_COUNT));
        // Step 2. Requesting connections count
        return restTemplate.getForObject(countURI, Integer.class);
    }

    @Override
    public Set<PlayerConnection> getConnections(String player) {
        // Step 1. Fetching player connections
        URI playerUri = buildUri(toConnectionUrl(PLAYER_CONNECTIONS), player);
        // Step 3. Requesting through RestTemplate
        return CollectionUtils.immutableSet(restTemplate.getForObject(playerUri, PlayerConnection[].class));
    }

    @Override
    public Integer getConnectionsCount(String player) {
        // Step 1. Building count URI
        URI playerCountUri = buildUri(toConnectionUrl(PLAYER_CONNECTIONS_COUNT), player);
        // Step 2. Requesting connections count
        return restTemplate.getForObject(playerCountUri, Integer.class);
    }

}
