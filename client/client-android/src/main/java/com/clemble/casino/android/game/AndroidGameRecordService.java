package com.clemble.casino.android.game;

import static com.clemble.casino.game.GameWebMapping.SESSIONS_RECORD;
import static com.clemble.casino.game.GameWebMapping.toGameUrl;

import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameRecord;
import com.clemble.casino.game.service.GameRecordService;

import java.net.URI;

public class AndroidGameRecordService extends AbstractClembleCasinoOperations implements GameRecordService {

    final private RestTemplate restTemplate;

    public AndroidGameRecordService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameRecord get(String session) {
        URI recordURL = buildUriWith(toGameUrl(SESSIONS_RECORD), session);
        return restTemplate.getForObject(recordURL, GameRecord.class);
    }

}
