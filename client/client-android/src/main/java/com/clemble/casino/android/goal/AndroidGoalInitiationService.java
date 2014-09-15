package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.construction.GoalConstruction;
import com.clemble.casino.goal.construction.GoalInitiation;
import com.clemble.casino.goal.construction.service.GoalInitiationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;

import static com.clemble.casino.goal.GoalWebMapping.GOAL_CONSTRUCTION_PENDING;
import static com.clemble.casino.goal.GoalWebMapping.GOAL_INITIATION_PENDING;
import static com.clemble.casino.goal.GoalWebMapping.toGoalConstructionUrl;

/**
 * Created by mavarazy on 9/15/14.
 */
public class AndroidGoalInitiationService extends AbstractClembleCasinoOperations implements GoalInitiationService{

    final private RestTemplate restTemplate;

    public AndroidGoalInitiationService(String apiBase, RestTemplate restTemplate) {
        super(apiBase);
        this.restTemplate = restTemplate;
    }

    @Override
    public Collection<GoalInitiation> getPending() {
        // Step 1. Generating goal construction URI
        URI pendingConstructionUrl = buildUriWith(toGoalConstructionUrl(GOAL_INITIATION_PENDING));
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(pendingConstructionUrl, GoalInitiation[].class));
    }

}
