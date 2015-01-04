package com.clemble.casino.goal.lifecycle.management;

import java.util.Set;

/**
 * Created by mavarazy on 1/4/15.
 */
public class ObserversGoalRoleExtractor implements GoalRoleExtractor {

    @Override
    public Set<String> extract(GoalRoleAware roleAware) {
        return roleAware.getObservers();
    }

}
