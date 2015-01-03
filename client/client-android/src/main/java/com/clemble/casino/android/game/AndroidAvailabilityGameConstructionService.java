package com.clemble.casino.android.game;

import java.util.Collection;

import com.clemble.casino.player.event.PlayerInvitationAction;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.construction.AvailabilityGameRequest;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;
import com.clemble.casino.game.lifecycle.construction.service.AvailabilityGameConstructionService;
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
        return restTemplate.postForObject(buildUri(toGameConstructionUrl(CONSTRUCTION_AVAILABILITY)), gameRequest, GameConstruction.class);
    }

    @Override
    public GameConstruction getConstruction(String sessionKey) {
        return restTemplate.getForObject(buildUri(toGameConstructionUrl(CONSTRUCTION), sessionKey), GameConstruction.class);
    }

    @Override
    public PlayerAction getReply(String sessionKey, String player) {
        return restTemplate.getForObject(buildUri(toGameConstructionUrl(CONSTRUCTION_RESPONSES_PLAYER), sessionKey, player), PlayerAction.class);
    }

    @Override
    public GameConstruction reply(String sessionKey, PlayerInvitationAction request) {
        return restTemplate.postForObject(buildUri(toGameConstructionUrl(CONSTRUCTION_RESPONSES), sessionKey), request, GameConstruction.class);
    }

    @Override
    public Collection<GameConstruction> getPending(String player) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUri(toGameConstructionUrl(CONSTRUCTION_AVAILABILITY_PENDING), player), GameConstruction[].class));
    }

}
