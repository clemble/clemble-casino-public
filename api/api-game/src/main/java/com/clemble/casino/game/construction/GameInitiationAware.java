package com.clemble.casino.game.construction;

import com.clemble.casino.lifecycle.initiation.InitiationAware;

public interface GameInitiationAware extends InitiationAware<GameInitiation> {

    @Override
    GameInitiation getInitiation();

}
