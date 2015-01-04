package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.utils.CollectionUtils;

import java.util.Set;

/**
 * Created by mavarazy on 1/4/15.
 */
public class PlayerGoalRoleExtractor implements GoalRoleExtractor{

    @Override
    public Set<String> extract(GoalRoleAware roleAware) {
        return CollectionUtils.immutableSet(roleAware.getPlayer());
    }

}
