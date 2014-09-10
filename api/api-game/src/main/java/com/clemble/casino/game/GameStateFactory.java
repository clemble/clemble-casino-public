package com.clemble.casino.game;

import com.clemble.casino.game.construction.GameInitiation;

public interface GameStateFactory<State extends GameState> extends GameAware {

    public State constructState(final GameInitiation initiation, final RoundGameContext context);

}
