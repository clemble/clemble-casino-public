package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.MY_RECORDS;
import static com.clemble.casino.game.GameWebMapping.SESSIONS_RECORD;
import static com.clemble.casino.game.GameWebMapping.toGameUrl;

import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.lifecycle.record.GameRecord;
import com.clemble.casino.game.lifecycle.record.service.GameRecordService;

import java.net.URI;
import java.util.List;

public class AndroidGameRecordService extends AbstractClembleCasinoOperations implements GameRecordService {

    final private RestTemplate restTemplate;

    public AndroidGameRecordService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GameRecord> myRecords() {
        URI recordURL = buildUriWith(toGameUrl(MY_RECORDS));
        return CollectionUtils.immutableList(restTemplate.getForObject(recordURL, GameRecord[].class));
    }

    @Override
    public GameRecord get(String session) {
        URI recordURL = buildUriWith(toGameUrl(SESSIONS_RECORD), session);
        return restTemplate.getForObject(recordURL, GameRecord.class);
    }

}
