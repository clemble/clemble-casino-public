package com.clemble.casino.game.service;

import com.clemble.casino.game.GameContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.web.game.GameWebMapping;
import com.clemble.casino.web.mapping.WebMapping;

public interface GameActionService extends ClembleService {

    public GameState getState(Game game, String session);

    public GameContext<?> getContext(Game game, String session);

    public GameManagementEvent process(Game game, String session, GameAction move);

}
