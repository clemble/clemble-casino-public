package com.clemble.casino.goal.service;

import com.clemble.casino.AdvancedClembleService;
import com.clemble.casino.goal.GoalJudgeInvitation;
import com.clemble.casino.goal.GoalKey;

import java.util.Collection;

/**
 * Created by mavarazy on 8/17/14.
 * TODO The only option to have an invitation to be a judge is to specify judge in GoalRequest
 */
public interface GoalJudgeInvitationService extends AdvancedClembleService {

    public Collection<GoalJudgeInvitation> myDuties();

    public Collection<GoalJudgeInvitation> myInvitations();

    public Collection<GoalJudgeInvitation> myDutiesAndInvitations();

    public GoalJudgeInvitation reply(GoalKey goalKey, GoalJudgeInvitation response);

}
