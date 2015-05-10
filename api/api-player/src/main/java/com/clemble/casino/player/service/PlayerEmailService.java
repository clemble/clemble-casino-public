package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.PlayerService;

/**
 * Created by mavarazy on 12/8/14.
 */
public interface PlayerEmailService extends PlayerService {

    String myEmail();

    boolean verify(String verificationCode);

}
