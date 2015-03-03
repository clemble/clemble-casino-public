package com.clemble.casino.notification;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface PlayerNotification extends PlayerEvent, CreatedAware {

    @Id
    String getKey();

    String getPlayer();

    @Override
    DateTime getCreated();

}
