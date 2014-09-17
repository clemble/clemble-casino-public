package com.clemble.casino.android.game;

import java.util.Collection;

import com.clemble.casino.game.construction.event.GameInvitationResponseEvent;
import com.clemble.casino.player.event.PlayerEvent;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construction.AvailabilityGameRequest;
import com.clemble.casino.game.construction.GameConstruction;
import com.clemble.casino.game.construction.service.AvailabilityGameConstructionService;
import com.clemble.casino.utils.CollectionUtils;

import static com.clemble.casino.game.GameWebMapping.*;

public class AndroidAvailabilityGameConstructionService<T extends GameState> extends AbstractClembleCasinoOperations implements AvailabilityGameConstructionService {

    final private RestTemplate restTemplate;

    public AndroidAvailabilityGameConstructionService(RestTemplate restTemplate, String apiBase) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public GameConstruction construct(AvailabilityGameRequest gameRequest) {
        return restTemplate.postForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION_AVAILABILITY)), gameRequest, GameConstruction.class);
    }

    @Override
    public GameConstruction getConstruction(String sessionKey) {
        return restTemplate.getForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION), sessionKey), GameConstruction.class);
    }

    @Override
    public PlayerEvent getReply(String sessionKey, String player) {
        return restTemplate.getForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION_RESPONSES_PLAYER), sessionKey, player), PlayerEvent.class);
    }

    @Override
    public GameConstruction reply(GameInvitationResponseEvent request) {
        return restTemplate.postForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION_RESPONSES), request.getSessionKey()), request, GameConstruction.class);
    }

    @Override
    public Collection<GameConstruction> getPending(String player) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toGameConstructionUrl(CONSTRUCTION_AVAILABILITY_PENDING), player), GameConstruction[].class));
    }

}
