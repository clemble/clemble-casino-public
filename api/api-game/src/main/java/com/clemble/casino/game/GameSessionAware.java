package com.clemble.casino.game;

import java.io.Serializable;

public interface GameSessionAware extends Serializable {

    final public String SESSION_KEY = "sessionKey";
    final GameSessionKey DEFAULT_SESSION = new GameSessionKey();

    GameSessionKey getSessionKey();

}
