package com.clemble.casino.goal.post;

import com.clemble.casino.bet.BetsAllowedAware;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.management.GoalRoleAware;
import com.clemble.casino.lifecycle.configuration.rule.time.DeadlineAware;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.BankAware;
import com.clemble.casino.post.PlayerPost;

/**
 * Created by mavarazy on 11/29/14.
 */
// TODO separate Goal notifications to Goal posts
public interface GoalPost extends
    PlayerPost,
    GoalAware,
    GoalDescriptionAware,
    GoalConfigurationAware,
    BankAware,
    BetsAllowedAware,
    GoalRoleAware,
    DeadlineAware {
}
