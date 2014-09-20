package com.clemble.casino.goal.construction;

import com.clemble.casino.construction.InitiationAware;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface GoalInitiationAware extends InitiationAware<GoalInitiation> {

    @Override
    GoalInitiation getInitiation();

}
