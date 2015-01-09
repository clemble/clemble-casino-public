package com.clemble.casino.notification;

import com.clemble.casino.player.event.PlayerEvent;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
public interface PlayerNotification extends PlayerEvent {

    @Id
    public String getKey();

    public String getPlayer();

    public Date getCreated();

}
