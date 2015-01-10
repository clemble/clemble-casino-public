package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalPost;
import com.clemble.casino.post.PlayerPostConvertible;

/**
 * Created by mavarazy on 10/9/14.
 */
public interface GoalManagementEvent extends GoalEvent<GoalState>, PlayerPostConvertible {

    GoalPost toPost();

}
