package com.clemble.casino.post;

import com.clemble.casino.player.event.PlayerEvent;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 11/30/14.
 */
public interface PlayerPost extends PlayerEvent {

    @Id
    String getKey();

    Date getCreated();

}
