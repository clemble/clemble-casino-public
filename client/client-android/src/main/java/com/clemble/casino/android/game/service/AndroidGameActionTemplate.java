package com.clemble.casino.android.game.service;

import static com.clemble.casino.utils.Preconditions.checkNotNull;
import static com.clemble.casino.web.game.GameWebMapping.GAME_SESSIONS_ACTIONS;
import static com.clemble.casino.web.game.GameWebMapping.GAME_SESSIONS_ACTIONS_ACTION;
import static com.clemble.casino.web.game.GameWebMapping.GAME_SESSIONS_STATE;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.ServerRegistry;
import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.client.game.ClientGameActionOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.game.event.server.GameManagementEvent;

public class AndroidGameActionTemplate extends AbstractClembleCasinoOperations implements ClientGameActionOperations {

    final private RestTemplate restTemplate;

    public AndroidGameActionTemplate(ServerRegistry apiBase, RestTemplate restTemplate) {
        super(apiBase);
        this.restTemplate = checkNotNull(restTemplate);
    }

    @Override
    public GameState getState(Game game, String session) {
        return restTemplate.getForObject(buildUriWith(GAME_SESSIONS_STATE, game, session), GameState.class);
    }

    @Override
    public GameManagementEvent process(Game game, String session, GameAction move) {
        return restTemplate.postForObject(buildUriWith(GAME_SESSIONS_ACTIONS, game, session), move, GameManagementEvent.class);
    }

    @Override
    public MadeMove getAction(Game game, String session, int action) {
        return restTemplate.getForObject(buildUriWith(GAME_SESSIONS_ACTIONS_ACTION, game, session, action), MadeMove.class);
    }

}
