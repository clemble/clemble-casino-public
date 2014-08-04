package com.clemble.casino.android.game;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.game.GameRecord;
import com.clemble.casino.game.service.GameRecordService;
import com.clemble.casino.goal.Goal;
import com.clemble.casino.goal.service.GoalService;
import com.clemble.casino.player.PlayerPresence;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collection;

import static com.clemble.casino.goal.GoalWebMapping.toGoalUrl;
import static com.clemble.casino.goal.GoalWebMapping.*;
import static com.clemble.casino.web.player.PlayerWebMapping.PROFILE_PLAYER;
import static com.clemble.casino.web.player.PlayerWebMapping.toProfileUrl;

/**
 * Created by mavarazy on 8/2/14.
 */
public class AndroidGoalService extends AbstractClembleCasinoOperations implements GoalService {

    final private RestTemplate restTemplate;

    public AndroidGoalService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public Goal addMyGoal(Goal goal) {
        // Step 1. Generating player URI
        URI myGoalsUrl = buildUriWith(toGoalUrl(MY_GOALS));
        // Step 2. Post to Player URI
        return restTemplate.postForObject(myGoalsUrl, goal, Goal.class);
    }

    @Override
    public Collection<Goal> myGoals() {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(MY_GOALS)), Goal[].class));
    }

    @Override
    public Collection<Goal> myPendingGoals() {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(MY_GOALS_PENDING)), Goal[].class));
    }

    @Override
    public Collection<Goal> myReachedGoals() {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(MY_GOALS_REACHED)), Goal[].class));
    }

    @Override
    public Collection<Goal> myMissedGoals() {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(MY_GOALS_MISSED)), Goal[].class));
    }

    @Override
    public Collection<Goal> getGoals(String player) {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(PLAYER_GOALS), player), Goal[].class));
    }

    @Override
    public Collection<Goal> getPendingGoals(String player) {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(PLAYER_GOALS_PENDING), player), Goal[].class));
    }

    @Override
    public Collection<Goal> getReachedGoals(String player) {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(PLAYER_GOALS_REACHED), player), Goal[].class));
    }

    @Override
    public Collection<Goal> getMissedGoals(String player) {
        return CollectionUtils.<Goal>immutableList(restTemplate.getForObject(buildUriWith(toGoalUrl(PLAYER_GOALS_MISSED), player), Goal[].class));
    }

}
