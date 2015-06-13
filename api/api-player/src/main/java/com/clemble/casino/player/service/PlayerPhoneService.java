package com.clemble.casino.player.service;

import com.clemble.casino.PlayerService;
import com.clemble.casino.player.PlayerPhone;
import com.clemble.casino.player.PlayerPhoneVerification;

/**
 * Created by mavarazy on 12/8/14.
 */
public interface PlayerPhoneService extends PlayerService {

    boolean add(PlayerPhone phone);

    boolean remove();

    boolean verify(PlayerPhoneVerification code);

}
