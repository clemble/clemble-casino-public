package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.web.player.PlayerWebMapping.*;

import java.net.URI;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.service.PlayerConnectionService;
import com.clemble.casino.utils.CollectionUtils;

public class AndroidPlayerConnectionService extends AbstractClembleCasinoOperations implements PlayerConnectionService {

    final private RestTemplate restTemplate;

    public AndroidPlayerConnectionService(RestTemplate restClientService, String host) {
        super(host);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public List<String> getConnections(String player) {
        // Step 1. Fetching player connections
        URI playerUri = buildUriWith(toConnectionUrl(CONNECTION_PLAYER), player);
       // Step 3. Requesting through RestTemplate
       return CollectionUtils.immutableList(restTemplate.getForObject(playerUri, String[].class));
    }

}
