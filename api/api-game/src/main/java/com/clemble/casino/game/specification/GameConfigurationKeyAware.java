package com.clemble.casino.game.specification;

import java.io.Serializable;

public interface GameConfigurationKeyAware extends Serializable {

    public GameConfigurationKey getConfigurationKey();

}
