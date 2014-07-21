package com.clemble.casino.android.game.service;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.web.game.GameWebMapping.*;

import com.clemble.casino.game.GameContext;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.client.game.ClientGameActionOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;

public class AndroidGameActionTemplate extends AbstractClembleCasinoOperations implements ClientGameActionOperations {

    final private RestTemplate restTemplate;

    public AndroidGameActionTemplate(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = checkNotNull(restTemplate);
    }

    @Override
    public GameState getState(Game game, String session) {
        return restTemplate.getForObject(buildUriWith(toGameUrl(SESSIONS_STATE), game, session), GameState.class);
    }

    @Override
    public GameContext<?> getContext(Game game, String session) {
        return restTemplate.getForObject(buildUriWith(toGameUrl(SESSIONS_CONTEXT), game, session), GameContext.class);
    }

    @Override
    public GameManagementEvent process(Game game, String session, GameAction move) {
        return restTemplate.postForObject(buildUriWith(toGameUrl(SESSIONS_ACTIONS), game, session), move, GameManagementEvent.class);
    }

}
