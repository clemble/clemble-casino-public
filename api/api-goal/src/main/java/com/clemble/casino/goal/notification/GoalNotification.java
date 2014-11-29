package com.clemble.casino.goal.notification;

import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.lifecycle.configuration.rule.time.DeadlineAware;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.BankAware;

/**
 * Created by mavarazy on 11/29/14.
 */
public interface GoalNotification extends
    PlayerNotification,
    GoalAware,
    GoalDescriptionAware,
    BankAware,
    DeadlineAware {
}
