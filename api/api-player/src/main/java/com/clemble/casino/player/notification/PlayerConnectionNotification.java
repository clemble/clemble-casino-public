package com.clemble.casino.player.notification;

import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.player.PlayerConnectionAware;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface PlayerConnectionNotification extends PlayerNotification, PlayerConnectionAware {

}
