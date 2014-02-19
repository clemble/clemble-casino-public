package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.game.GameRecord;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.service.GameRecordService;

public class GameRecordTemplate implements GameRecordOperations {

    final private GameRecordService gameRecordService;

    public GameRecordTemplate(GameRecordService gameRecordService) {
        this.gameRecordService = checkNotNull(gameRecordService);
    }

    @Override
    public GameRecord get(GameSessionKey sessionKey) {
        return gameRecordService.get(sessionKey.getGame(), sessionKey.getSession());
    }

}
