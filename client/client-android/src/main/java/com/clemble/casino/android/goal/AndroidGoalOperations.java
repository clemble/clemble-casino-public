package com.clemble.casino.android.goal;

import com.clemble.casino.client.goal.GoalOperations;
import com.clemble.casino.goal.lifecycle.configuration.service.GoalConfigurationService;
import com.clemble.casino.goal.lifecycle.construction.service.GoalConstructionService;
import com.clemble.casino.goal.lifecycle.construction.service.GoalSuggestionService;
import com.clemble.casino.goal.lifecycle.management.service.GoalActionService;
import com.clemble.casino.goal.lifecycle.management.service.GoalVictoryService;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mavarazy on 9/15/14.
 */
public class AndroidGoalOperations implements GoalOperations{

    final private GoalConfigurationService configurationService;
    final private GoalConstructionService constructionService;
    final private GoalSuggestionService suggestionService;
    final private GoalActionService actionService;
    final private GoalVictoryService victoryService;

    public AndroidGoalOperations(String host, RestTemplate restTemplate) {
        this.configurationService = new AndroidGoalConfigurationService(host, restTemplate);
        this.constructionService = new AndroidGoalConstructionService(host, restTemplate);
        this.suggestionService = new AndroidGoalSuggestionService(host, restTemplate);
        this.actionService = new AndroidGoalActionService(host, restTemplate);
        this.victoryService = new AndroidGoalVictoryService(host, restTemplate);
    }

    @Override
    public GoalConfigurationService configurationService() {
        return configurationService;
    }

    @Override
    public GoalConstructionService constructionService() {
        return constructionService;
    }

    @Override
    public GoalSuggestionService suggestionService() {
        return suggestionService;
    }

    @Override
    public GoalActionService actionService() {
        return actionService;
    }

    @Override
    public GoalVictoryService victoryService() {
        return victoryService;
    }

}
