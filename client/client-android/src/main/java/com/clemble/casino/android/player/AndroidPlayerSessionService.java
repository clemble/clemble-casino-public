package com.clemble.casino.android.player;

import java.net.URI;

import static com.clemble.casino.web.player.PlayerWebMapping.*;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.security.PlayerSession;
import com.clemble.casino.player.service.PlayerSessionService;

public class AndroidPlayerSessionService extends AbstractClembleCasinoOperations implements PlayerSessionService {

    final private RestTemplate restClientService;

    public AndroidPlayerSessionService(RestTemplate restClientService, String host) {
        super(host);
        this.restClientService = restClientService;
    }

    @Override
    public PlayerSession create(String player) {
        URI uri = buildUriWith(toPresenceUrl(PRESENCE_SESSIONS_PLAYER), player);
        return restClientService
            .postForEntity(uri, null, PlayerSession.class)
            .getBody();
    }

    @Override
    public PlayerSession refreshPlayerSession(String player, String sessionId) {
        URI uri = buildUriWith(toPresenceUrl(PRESENCE_SESSIONS_PLAYER_SESSION), player, sessionId);
        return restClientService.postForObject(uri, null, PlayerSession.class);
    }

    @Override
    public void endPlayerSession(String player, String sessionId) {
        // Step 1. Building session URI
        URI sessionUri = buildUriWith(toPresenceUrl(PRESENCE_SESSIONS_PLAYER_SESSION), player, sessionId);
        // Step 2. Calling post for the generated URI
        restClientService.delete(sessionUri);
    }

    @Override
    public PlayerSession getPlayerSession(String player, String sessionId) {
        // Step 1. Building session URI
        URI sessionUri = buildUriWith(toPresenceUrl(PRESENCE_SESSIONS_PLAYER_SESSION), player, sessionId);
        // Step 2. Calling rest service
        return restClientService.getForObject(sessionUri, PlayerSession.class);
    }

}
