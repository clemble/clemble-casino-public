package com.clemble.casino.client.game;

import com.clemble.casino.game.GameRecord;
import com.clemble.casino.game.GameSessionKey;

public interface GameRecordOperations {

    public GameRecord get(GameSessionKey sessionKey);

}
