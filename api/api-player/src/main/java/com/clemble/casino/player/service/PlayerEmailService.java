package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;

/**
 * Created by mavarazy on 12/8/14.
 */
public interface PlayerEmailService extends ClembleService {

    boolean verify(String verificationCode);

}
