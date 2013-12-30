package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.player.service.PlayerConnectionService;
import com.clemble.casino.web.player.PlayerWebMapping;

public class AndroidPlayerConnectionService extends AbstractClembleCasinoOperations implements PlayerConnectionService {

    final private RestTemplate restTemplate;

    public AndroidPlayerConnectionService(RestTemplate restClientService, ServerRegistry serverRegistry) {
        super(serverRegistry);
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public List<String> getConnections(String player) {
        // Step 1. Fething player connections
        URI playerUri = buildUriWith(PlayerWebMapping.PLAYER_CONNECTIONS, player);
       // Step 3. Requesting through RestTemplate
       return restTemplate
           .getForEntity(playerUri, List.class)
           .getBody();
    }
}
