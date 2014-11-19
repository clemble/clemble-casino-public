package com.clemble.casino.client.goal;

import com.clemble.casino.goal.lifecycle.configuration.service.GoalConfigurationService;
import com.clemble.casino.goal.lifecycle.construction.service.GoalConstructionService;
import com.clemble.casino.goal.lifecycle.initiation.service.FriendInitiationService;
import com.clemble.casino.goal.lifecycle.initiation.service.GoalInitiationService;
import com.clemble.casino.goal.lifecycle.management.service.GoalActionService;
import com.clemble.casino.goal.lifecycle.management.service.FriendGoalService;
import com.clemble.casino.goal.lifecycle.record.service.GoalRecordService;

/**
 * Created by mavarazy on 9/15/14.
 */
public interface GoalOperations {

    GoalConfigurationService configurationService();

    GoalConstructionService constructionService();

    GoalInitiationService initiationService();

    FriendInitiationService friendInitiationService();

    GoalActionService actionService();

    FriendGoalService friendGoalService();

    GoalRecordService recordService();

}
