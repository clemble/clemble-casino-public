package com.clemble.casino.game.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameRecord;

public interface GameRecordService extends ClembleService {

    public GameRecord get(Game game, String session);

}
