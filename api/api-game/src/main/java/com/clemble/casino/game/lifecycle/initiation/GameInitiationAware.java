package com.clemble.casino.game.lifecycle.initiation;

import com.clemble.casino.lifecycle.initiation.InitiationAware;

public interface GameInitiationAware extends InitiationAware<GameInitiation> {

    @Override
    GameInitiation getInitiation();

}
