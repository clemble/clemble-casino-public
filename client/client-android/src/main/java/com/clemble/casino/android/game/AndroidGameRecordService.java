package com.clemble.casino.android.game;

import com.clemble.casino.lifecycle.record.RecordState;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.lifecycle.record.GameRecord;
import com.clemble.casino.game.lifecycle.record.service.GameRecordService;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.game.GameWebMapping.*;

public class AndroidGameRecordService extends AbstractClembleCasinoOperations implements GameRecordService {

    final private RestTemplate restTemplate;

    public AndroidGameRecordService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GameRecord> myRecords() {
        URI recordURL = buildUri(toGameUrl(MY_RECORDS));
        return CollectionUtils.immutableList(restTemplate.getForObject(recordURL, GameRecord[].class));
    }

    @Override
    public List<GameRecord> myRecordsWithState(RecordState states) {
        URI recordURL = buildUri(toGameUrl(MY_RECORDS_STATE), states);
        return CollectionUtils.immutableList(restTemplate.getForObject(recordURL, GameRecord[].class));
    }

    @Override
    public GameRecord get(String session) {
        URI recordURL = buildUri(toGameUrl(SESSIONS_RECORD), session);
        return restTemplate.getForObject(recordURL, GameRecord.class);
    }

}
