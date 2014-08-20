package com.clemble.casino.game;

import com.clemble.casino.KeyAware;

import java.io.Serializable;

public interface GameSessionAware extends KeyAware {

    final public String SESSION_KEY = "sessionKey";
    final public String TRANSACTION_TOKEN = "GAME";
    final String DEFAULT_SESSION = "";

    String getSessionKey();

}
