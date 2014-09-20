package com.clemble.casino.game.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameRecord;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.management.service.RecordService;

import java.util.List;

public interface GameRecordService extends RecordService<GameConfiguration> {

    @Override
    public List<GameRecord> myRecords();

    @Override
    public GameRecord get(String sessionKey);

}
