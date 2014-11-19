package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.lifecycle.initiation.service.FriendInitiationService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static com.clemble.casino.goal.GoalWebMapping.*;
import static com.clemble.casino.goal.GoalWebMapping.toGoalManagementUrl;

/**
 * Created by mavarazy on 11/19/14.
 */
public class AndroidFriendInitiationService extends AbstractClembleCasinoOperations implements FriendInitiationService {

    final private RestTemplate restTemplate;

    public AndroidFriendInitiationService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }


    @Override
    public List<GoalInitiation> myFriendInitiations() {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(MY_FRIEND_INITIATIONS));
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(actionUrl, GoalInitiation[].class));
    }

    @Override
    public GoalInitiation myFriendInitiation(String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(MY_FRIEND_INITIATION), goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalInitiation.class);
    }

    @Override
    public List<GoalInitiation> getFriendInitiations(String player) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(PLAYER_FRIEND_INITIATIONS), player);
        // Step 2. Creating new GoalConstruction
        return CollectionUtils.immutableList(restTemplate.getForObject(actionUrl, GoalInitiation[].class));
    }

    @Override
    public GoalInitiation getFriendInitiation(String player, String goalKey) {
        // Step 1. Generating goal construction URI
        URI actionUrl = buildUriWith(toGoalManagementUrl(PLAYER_FRIEND_INITIATION), player, goalKey);
        // Step 2. Creating new GoalConstruction
        return restTemplate.getForObject(actionUrl, GoalInitiation.class);
    }

}
