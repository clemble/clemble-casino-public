package com.clemble.casino.android.player;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.security.PlayerSession;
import com.clemble.casino.player.service.PlayerSessionService;
import static com.clemble.casino.web.management.ManagementWebMapping.*;

public class AndroidPlayerSessionService extends AbstractClembleCasinoOperations implements PlayerSessionService {

    final private RestTemplate restClientService;

    public AndroidPlayerSessionService(RestTemplate restClientService, ServerRegistry serverRegistry) {
        super(serverRegistry);
        this.restClientService = restClientService;
    }

    @Override
    public PlayerSession create(String player) {
        URI uri = buildUriWith(MANAGEMENT_PLAYER_SESSIONS, player);
        return restClientService
            .postForEntity(uri, null, PlayerSession.class)
            .getBody();
    }

    @Override
    public PlayerSession refreshPlayerSession(String player, long sessionId) {
        URI uri = buildUriWith(MANAGEMENT_PLAYER_SESSIONS_SESSION, player, sessionId);
        return restClientService.postForObject(uri, null, PlayerSession.class);
    }

    @Override
    public void endPlayerSession(String player, long sessionId) {
        // Step 1. Building session URI
        URI sessionUri = buildUriWith(MANAGEMENT_PLAYER_SESSIONS_SESSION, player, sessionId);
        // Step 2. Calling post for the generated URI
        restClientService.delete(sessionUri);
    }

    @Override
    public PlayerSession getPlayerSession(String player, long sessionId) {
        // Step 1. Building session URI
        URI sessionUri = buildUriWith(MANAGEMENT_PLAYER_SESSIONS_SESSION, player, sessionId);
        // Step 2. Calling rest service
        return restClientService.getForObject(sessionUri, PlayerSession.class);
    }

}
