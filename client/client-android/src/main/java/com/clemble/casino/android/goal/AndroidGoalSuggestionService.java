package com.clemble.casino.android.goal;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.goal.lifecycle.construction.GoalSuggestion;
import com.clemble.casino.goal.lifecycle.construction.GoalSuggestionRequest;
import com.clemble.casino.goal.lifecycle.construction.service.GoalSuggestionService;
import com.clemble.casino.utils.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import static com.clemble.casino.goal.GoalWebMapping.*;

import java.net.URI;
import java.util.List;

/**
 * Created by mavarazy on 1/3/15.
 */
public class AndroidGoalSuggestionService extends AbstractClembleCasinoOperations implements GoalSuggestionService {

    final private RestTemplate restTemplate;

    public AndroidGoalSuggestionService(String host, RestTemplate restTemplate) {
        super(host);
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GoalSuggestion> listMy() {
        // Step 1. Generating URI
        URI uri = buildUri(toGoalSuggestionUrl(MY_SUGGESTIONS));
        // Step 2. Processing Request
        return CollectionUtils.immutableList(restTemplate.getForObject(uri, GoalSuggestion[].class));
    }

    @Override
    public List<GoalSuggestion> list(String player) {
        // Step 1. Generate URI
        URI uri = buildUri(toGoalSuggestionUrl(PLAYER_SUGGESTIONS));
        // Step 2. Fetching suggestion
        return CollectionUtils.immutableList(restTemplate.getForObject(uri, GoalSuggestion[].class));
    }

    @Override
    public GoalSuggestion getSuggestion(String goalKey) {
        // Step 1. Generate URI
        URI uri = buildUri(toGoalSuggestionUrl(SUGGESTION), goalKey);
        // Step 2. Fetching suggestion
        return restTemplate.getForObject(uri, GoalSuggestion.class);
    }

    @Override
    public GoalSuggestion addSuggestion(String player, GoalSuggestionRequest suggestionRequest) {
        // Step 1. Generate URI
        URI uri = buildUri(toGoalSuggestionUrl(PLAYER_SUGGESTIONS), player);
        // Step 2. Fetch suggestion
        return restTemplate.postForObject(uri, suggestionRequest, GoalSuggestion.class);
    }

    @Override
    public GoalSuggestion reply(String goalKey, boolean accept) {
        // Step 1. Generating URI
        URI uri = buildUri(toGoalSuggestionUrl(MY_SUGGESTIONS_GOAL), goalKey);
        // Step 2. Publishing response
        return restTemplate.postForObject(uri, accept, GoalSuggestion.class);
    }

}
