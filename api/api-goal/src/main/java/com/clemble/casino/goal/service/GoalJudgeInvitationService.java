package com.clemble.casino.goal.service;

import com.clemble.casino.AdvancedClembleService;
import com.clemble.casino.goal.GoalJudgeInvitation;

import java.util.Collection;

/**
 * Created by mavarazy on 8/17/14.
 * TODO The only option to have an invitation to be a judge is to specify judge in GoalRequest
 */
public interface GoalJudgeInvitationService extends AdvancedClembleService {

    public Collection<GoalJudgeInvitation> myPending();

    public Collection<GoalJudgeInvitation> myAccepted();

    public Collection<GoalJudgeInvitation> myDeclined();

    public GoalJudgeInvitation reply(String goalKey, GoalJudgeInvitation response);

}
