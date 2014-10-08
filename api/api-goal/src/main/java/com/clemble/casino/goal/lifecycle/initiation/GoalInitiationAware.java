package com.clemble.casino.goal.lifecycle.initiation;

import com.clemble.casino.lifecycle.initiation.InitiationAware;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface GoalInitiationAware extends InitiationAware<GoalInitiation> {

    @Override
    GoalInitiation getInitiation();

}
