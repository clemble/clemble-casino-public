package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.event.Event;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.construction.GoalConstruction;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.lifecycle.management.service.GoalActionService;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static com.clemble.casino.goal.GoalWebMapping.*;

/**
 * Created by mavarazy on 10/9/14.
 */
public class AndroidGoalActionService extends AbstractClembleCasinoOperations implements GoalActionService {

    final private RestTemplate restTemplate;

    public AndroidGoalActionService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public GoalEvent process(String goalKey, Event action) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(GOAL_ACTIONS), goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.postForObject(actionUrl, action, GoalEvent.class);
    }

    @Override
    public GoalState getState(String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(GOAL_STATE), goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalState.class);
    }
}
