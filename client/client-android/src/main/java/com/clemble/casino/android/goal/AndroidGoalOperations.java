package com.clemble.casino.android.goal;

import com.clemble.casino.client.goal.GoalOperations;
import com.clemble.casino.goal.lifecycle.configuration.service.GoalConfigurationService;
import com.clemble.casino.goal.lifecycle.construction.service.GoalConstructionService;
import com.clemble.casino.goal.lifecycle.construction.service.GoalSuggestionService;
import com.clemble.casino.goal.lifecycle.initiation.service.FriendInitiationService;
import com.clemble.casino.goal.lifecycle.initiation.service.GoalInitiationService;
import com.clemble.casino.goal.lifecycle.management.service.GoalActionService;
import com.clemble.casino.goal.lifecycle.record.service.GoalRecordService;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mavarazy on 9/15/14.
 */
public class AndroidGoalOperations implements GoalOperations{

    final private GoalConfigurationService configurationService;
    final private GoalConstructionService constructionService;
    final private GoalSuggestionService suggestionService;
    final private GoalInitiationService initiationService;
    final private FriendInitiationService friendInitiationService;
    final private GoalActionService actionService;
    final private GoalRecordService recordService;

    public AndroidGoalOperations(String host, RestTemplate restTemplate) {
        this.configurationService = new AndroidGoalConfigurationService(host, restTemplate);
        this.constructionService = new AndroidGoalConstructionService(host, restTemplate);
        this.suggestionService = new AndroidGoalSuggestionService(host, restTemplate);
        this.initiationService = new AndroidGoalInitiationService(host, restTemplate);
        this.friendInitiationService = new AndroidFriendInitiationService(host, restTemplate);
        this.actionService = new AndroidGoalActionService(host, restTemplate);
        this.recordService = new AndroidGoalRecordService(host, restTemplate);
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
    public GoalInitiationService initiationService() {
        return initiationService;
    }

    @Override
    public FriendInitiationService friendInitiationService() {
        return friendInitiationService;
    }

    @Override
    public GoalActionService actionService() {
        return actionService;
    }

    @Override
    public GoalRecordService recordService() {
        return recordService;
    }

}
