package com.clemble.casino.notification;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface PlayerNotification extends PlayerAware {

}
