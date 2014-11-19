package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.lifecycle.management.service.FriendGoalService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.goal.GoalWebMapping.*;

/**
 * Created by mavarazy on 11/18/14.
 */
public class AndroidFriendGoalService extends AbstractClembleCasinoOperations implements FriendGoalService {

    final private RestTemplate restTemplate;

    public AndroidFriendGoalService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }


    @Override
    public List<GoalState> myFriendGoals() {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(MY_FRIEND_GOALS));
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(actionUrl, GoalState[].class));
    }

    @Override
    public GoalState myFriendGoal(String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(MY_FRIEND_GOAL), goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalState.class);
    }

    @Override
    public List<GoalState> getFriendGoals(String player) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(PLAYER_FRIEND_GOALS), player);
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(actionUrl, GoalState[].class));
    }

    @Override
    public GoalState getFriendGoal(String player, String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(PLAYER_FRIEND_GOALS), player, goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalState.class);
    }

}
