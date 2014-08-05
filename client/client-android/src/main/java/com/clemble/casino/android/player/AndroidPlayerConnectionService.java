package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.web.player.PlayerWebMapping.*;

import java.net.URI;
import java.util.List;

import com.clemble.casino.player.service.PlayerConnectionService;
import org.springframework.social.connect.ConnectionKey;
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
    public List<ConnectionKey> myConnections() {
        // Step 1. Fetching player connections
        URI playerUri = buildUriWith(toConnectionUrl(MY_CONNECTIONS));
       // Step 3. Requesting through RestTemplate
       return CollectionUtils.immutableList(restTemplate.getForObject(playerUri, ConnectionKey[].class));
    }

    @Override
    public List<ConnectionKey> getConnections(String player) {
        // Step 1. Fetching player connections
        URI playerUri = buildUriWith(toConnectionUrl(PLAYER_CONNECTIONS), player);
       // Step 3. Requesting through RestTemplate
       return CollectionUtils.immutableList(restTemplate.getForObject(playerUri, ConnectionKey[].class));
    }

}
