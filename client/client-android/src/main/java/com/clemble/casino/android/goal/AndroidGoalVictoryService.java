package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.lifecycle.management.service.GoalVictoryService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.goal.GoalWebMapping.*;

/**
 * Created by mavarazy on 3/14/15.
 */
public class AndroidGoalVictoryService extends AbstractClembleCasinoOperations implements GoalVictoryService {

    final private RestTemplate restTemplate;

    public AndroidGoalVictoryService(String apiBase, RestTemplate restTemplate) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GoalState> listMy() {
        // Step 1. Generating URL
        URI myVictories = buildUri(toGoalManagementUrl(MY_VICTORIES));
        // Step 2. Requesting for list of victories
        return CollectionUtils.immutableList(restTemplate.getForObject(myVictories, GoalState[].class));
    }

    @Override
    public List<GoalState> list(String player) {
        // Step 1. Generating URL
        URI victories = buildUri(toGoalManagementUrl(PLAYER_VICTORIES), player);
        // Step 2. Requesting for list of victories
        return CollectionUtils.immutableList(restTemplate.getForObject(victories, GoalState[].class));
    }

}
