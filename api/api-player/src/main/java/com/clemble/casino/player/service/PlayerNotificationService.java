package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;
import com.clemble.casino.notification.PlayerNotification;

import java.util.List;

/**
 * Created by mavarazy on 11/29/14.
 */
public interface PlayerNotificationService extends PlayerService {

    PlayerNotification[] myNotifications();

    void delete(String key);

}
