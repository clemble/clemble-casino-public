package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.construction.GoalConstruction;
import com.clemble.casino.goal.lifecycle.construction.GoalConstructionRequest;
import com.clemble.casino.goal.lifecycle.construction.service.GoalConstructionService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;

import static com.clemble.casino.goal.GoalWebMapping.*;

/**
 * Created by mavarazy on 9/15/14.
 */
public class AndroidGoalConstructionService extends AbstractClembleCasinoOperations implements GoalConstructionService {

    final private RestTemplate restTemplate;

    public AndroidGoalConstructionService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GoalConstruction construct(GoalConstructionRequest request) {
        // Step 1. Generating goal construction URI
        URI constructionUrl = buildUri(toGoalConstructionUrl(GOAL_CONSTRUCTION));
        // Step 2. Creating new GoalConstruction
        return restTemplate.postForObject(constructionUrl, request, GoalConstruction.class);
    }

    @Override
    public Collection<GoalConstruction> getPending(String player) {
        // Step 1. Generating goal construction URI
        URI pendingConstructionUrl = buildUri(toGoalConstructionUrl(GOAL_CONSTRUCTION_PENDING));
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(pendingConstructionUrl, GoalConstruction[].class));
    }

}
