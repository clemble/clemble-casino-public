package com.clemble.casino.android.goal;

import com.clemble.casino.client.goal.GoalOperations;
import com.clemble.casino.goal.lifecycle.configuration.service.GoalConfigurationService;
import com.clemble.casino.goal.lifecycle.construction.service.GoalConstructionService;
import com.clemble.casino.goal.lifecycle.initiation.service.GoalInitiationService;
import com.clemble.casino.goal.service.GoalJudgeDutyService;
import com.clemble.casino.goal.service.GoalJudgeInvitationService;
import com.clemble.casino.goal.service.GoalService;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mavarazy on 9/15/14.
 */
public class AndroidGoalOperations implements GoalOperations{

    final private GoalConfigurationService configurationService;
    final private GoalConstructionService constructionService;
    final private GoalInitiationService initiationService;
    final private GoalService service;
    final private GoalJudgeInvitationService judgeInvitationService;
    final private GoalJudgeDutyService dutyService;

    public AndroidGoalOperations(String host, RestTemplate restTemplate) {
        this.configurationService = new AndroidGoalConfigurationService(host, restTemplate);
        this.constructionService = new AndroidGoalConstructionService(host, restTemplate);
        this.initiationService = new AndroidGoalInitiationService(host, restTemplate);
        this.service = new AndroidGoalService(host, restTemplate);
        this.judgeInvitationService = new AndroidGoalJudgeInvitationService(host, restTemplate);
        this.dutyService = new AndroidGoalJudgeDutyService(host, restTemplate);
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
    public GoalInitiationService initiationService() {
        return initiationService;
    }

    @Override
    public GoalService goalService() {
        return service;
    }

    @Override
    public GoalJudgeInvitationService goalInvitationOperations() {
        return judgeInvitationService;
    }

    @Override
    public GoalJudgeDutyService goalDutyOperations() {
        return dutyService;
    }
}
