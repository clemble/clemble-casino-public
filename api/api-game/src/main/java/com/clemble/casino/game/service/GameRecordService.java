package com.clemble.casino.game.service;

import com.clemble.casino.game.GameRecord;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.lifecycle.management.service.RecordService;

import java.util.List;

public interface GameRecordService extends RecordService<GameConfiguration> {

    @Override
    public List<GameRecord> myRecords();

    @Override
    public GameRecord get(String sessionKey);

}
