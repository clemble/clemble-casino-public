package com.clemble.casino.game.specification;

import java.io.Serializable;

public interface GameConfigurationAware extends Serializable {

    GameConfiguration getConfiguration();

}
