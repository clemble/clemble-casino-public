package com.clemble.casino.android.player;

import java.net.URI;

import static com.clemble.casino.player.PlayerWebMapping.*;

import com.clemble.casino.player.service.PlayerSessionService;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.player.PlayerSession;

public class AndroidPlayerSessionService extends AbstractClembleCasinoOperations implements PlayerSessionService {

    final private String host;
    final private RestTemplate restClientService;

    public AndroidPlayerSessionService(RestTemplate restClientService, String host) {
        super(host);
        this.host = host;
        this.restClientService = restClientService;
    }

    @Override
    public PlayerSession create() {
        URI uri = buildUriWith(toPresenceUrl(host, PRESENCE_SESSIONS_PLAYER));
        return restClientService
            .postForEntity(uri, "{}", PlayerSession.class)
            .getBody();
    }

    @Override
    public PlayerSession refreshPlayerSession(String sessionId) {
        URI uri = buildUriWith(toPresenceUrl(host, PRESENCE_SESSIONS_PLAYER_SESSION), sessionId);
        return restClientService.postForObject(uri, null, PlayerSession.class);
    }

    @Override
    public void endPlayerSession(String sessionId) {
        // Step 1. Building session URI
        URI sessionUri = buildUriWith(toPresenceUrl(host, PRESENCE_SESSIONS_PLAYER_SESSION), sessionId);
        // Step 2. Calling post for the generated URI
        restClientService.delete(sessionUri);
    }

    @Override
    public PlayerSession getPlayerSession(String sessionId) {
        // Step 1. Building session URI
        URI sessionUri = buildUriWith(toPresenceUrl(host, PRESENCE_SESSIONS_PLAYER_SESSION), sessionId);
        // Step 2. Calling rest service
        return restClientService.getForObject(sessionUri, PlayerSession.class);
    }

}
