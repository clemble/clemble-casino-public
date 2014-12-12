package com.clemble.casino.notification;

import com.clemble.casino.player.event.PlayerEvent;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
public interface PlayerNotification extends PlayerEvent {

    public String getKey();

    public Date getCreated();

}
