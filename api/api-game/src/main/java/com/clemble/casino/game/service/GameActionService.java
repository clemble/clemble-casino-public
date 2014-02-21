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

    @RequestMapping(method = RequestMethod.GET, value = GameWebMapping.GAME_SESSIONS_STATE, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody GameState getState(@PathVariable("game") Game game, @PathVariable("session") String session);

    @RequestMapping(method = RequestMethod.GET, value = GameWebMapping.GAME_SESSIONS_CONTEXT, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody GameContext<?> getContext(@PathVariable("game") Game game, @PathVariable("session") String session);

    @RequestMapping(method = RequestMethod.POST, value = GameWebMapping.GAME_SESSIONS_ACTIONS, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody GameManagementEvent process(@PathVariable("game") Game game, @PathVariable("session") String session, @RequestBody GameAction move);

    @RequestMapping(method = RequestMethod.GET, value = GameWebMapping.GAME_SESSIONS_ACTIONS_ACTION, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody MadeMove getAction(@PathVariable("game") Game game, @PathVariable("sessionId") String session, @PathVariable("actionId") int actionId);
}
