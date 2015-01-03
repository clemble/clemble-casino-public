package com.clemble.casino.android.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.game.GameWebMapping.*;

import com.clemble.casino.game.lifecycle.management.GameContext;
import com.clemble.casino.game.lifecycle.management.service.GameActionService;
import com.clemble.casino.lifecycle.management.event.action.Action;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;

public class AndroidGameActionTemplate extends AbstractClembleCasinoOperations implements GameActionService {

    final private RestTemplate restTemplate;

    public AndroidGameActionTemplate(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = checkNotNull(restTemplate);
    }

    @Override
    public GameState getState(String sessionKey) {
        return restTemplate.getForObject(buildUri(toGameUrl(SESSIONS_STATE), sessionKey), GameState.class);
    }

    @Override
    public GameContext<?> getContext(String sessionKey) {
        return restTemplate.getForObject(buildUri(toGameUrl(SESSIONS_CONTEXT), sessionKey), GameContext.class);
    }

    @Override
    public GameManagementEvent process(String sessionKey, Action move) {
        return restTemplate.postForObject(buildUri(toGameUrl(SESSIONS_ACTIONS), sessionKey), move, GameManagementEvent.class);
    }

}
