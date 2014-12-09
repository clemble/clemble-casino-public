package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.player.PlayerPhone;

/**
 * Created by mavarazy on 12/8/14.
 */
public interface PlayerPhoneService extends ClembleService {

    public boolean add(PlayerPhone phone);

    public boolean remove();

    public boolean verify(String code);

}
