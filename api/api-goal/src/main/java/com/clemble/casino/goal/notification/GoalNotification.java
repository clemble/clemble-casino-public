package com.clemble.casino.goal.notification;

import com.clemble.casino.notification.PlayerNotification;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 3/3/15.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GoalNotification extends PlayerNotification {
}
