package com.clemble.casino.android.player;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import com.clemble.casino.player.service.PlayerPresenceService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.utils.CollectionUtils;
import static com.clemble.casino.player.PlayerWebMapping.*;

public class AndroidPlayerPresenceService extends AbstractClembleCasinoOperations implements PlayerPresenceService {

    final private String host;
    final private RestTemplate restTemplate;

    public AndroidPlayerPresenceService(RestTemplate restClientService, String host) {
        super(host);
        this.host = host;
        this.restTemplate = checkNotNull(restClientService);
    }

    @Override
    public PlayerPresence myPresence() {
        URI presenceURI = buildUriWith(toPresenceUrl(host, MY_PRESENCE));
        // Step 1. Singleton GET request
        return restTemplate.getForObject(presenceURI, PlayerPresence.class);
    }

    @Override
    public PlayerPresence getPresence(String player) {
        URI presenceURI = buildUriWith(toPresenceUrl(host, PLAYER_PRESENCE), player);
        // Step 1. Singleton GET request
        return restTemplate.getForObject(presenceURI, PlayerPresence.class);
    }

    @Override
    public List<PlayerPresence> getPresences(String ... players) {
        return getPresences(Arrays.asList(players));
    }

    @Override
    public List<PlayerPresence> getPresences(List<String> players) {
        // Step 1. Sanity check
        if (players == null || players.isEmpty())
            return CollectionUtils.immutableList();
        // Step 2. Generating multivalue query map
        MultiValueMap<String, String> query = new LinkedMultiValueMap<String, String>();
        for (String player : players)
            query.set(PLAYER_PRESENCES_PARAM, player);
        // Step 3. Requesting through RestTemplate
        return CollectionUtils.<PlayerPresence> immutableList(restTemplate.getForObject(buildUri(toPresenceUrl(host, PLAYER_PRESENCES), query), PlayerPresence[].class));
    }
}
