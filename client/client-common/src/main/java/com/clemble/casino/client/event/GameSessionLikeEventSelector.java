package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;

import java.util.regex.Pattern;

/**
 * Constructs GameSessionLike pattern to match all related sessions
 */
public class GameSessionLikeEventSelector implements EventSelector {

    final private Game game;
    final private Pattern matchPattern;

    public GameSessionLikeEventSelector(Game game, String pattern) {
        this.game = game;
        this.matchPattern = Pattern.compile(pattern);
    }

    @Override
    public boolean filter(Event event) {
        // Step 1. Checking game session aware event
        if (!(event instanceof GameSessionAware))
            return false;
        // Step 2. Checking session key matches expected one
        GameSessionKey sessionKey = ((GameSessionAware) event).getSession();
        return (event instanceof GameSessionAware)
                && game.equals(sessionKey.getGame())
                && matchPattern.matcher(sessionKey.getSession()).matches();
    }
}