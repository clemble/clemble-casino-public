package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.lifecycle.management.service.GoalTimelineService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.goal.GoalWebMapping.*;

/**
 * Created by mavarazy on 11/18/14.
 */
public class AndroidGoalTimelineService extends AbstractClembleCasinoOperations implements GoalTimelineService {

    final private RestTemplate restTemplate;

    public AndroidGoalTimelineService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }


    @Override
    public List<GoalState> myConnectionsTimeLine() {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(MY_CONNECTIONS_TIMELINES));
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(actionUrl, GoalState[].class));
    }

    @Override
    public GoalState myConnectionTimeLine(String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(MY_CONNECTIONS_TIMELINES_GOAL), goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalState.class);
    }

    @Override
    public List<GoalState> getConnectionsTimeLine(String player) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(PLAYER_CONNECTIONS_TIMELINES), player);
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(actionUrl, GoalState[].class));
    }

    @Override
    public GoalState getConnectionTimeLine(String player, String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(PLAYER_CONNECTIONS_TIMELINES), player, goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalState.class);
    }

}
