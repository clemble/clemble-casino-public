package com.clemble.casino.notification;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.player.event.PlayerEvent;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
public interface PlayerNotification extends PlayerEvent, CreatedAware {

    @Id
    String getKey();

    String getPlayer();

    Date getCreated();

}
