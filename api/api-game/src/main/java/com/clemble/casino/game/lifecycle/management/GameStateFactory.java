package com.clemble.casino.game.lifecycle.management;

import com.clemble.casino.game.GameAware;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;

public interface GameStateFactory<State extends GameState> extends GameAware {

    public State constructState(final GameInitiation initiation, final RoundGameContext context);

}
