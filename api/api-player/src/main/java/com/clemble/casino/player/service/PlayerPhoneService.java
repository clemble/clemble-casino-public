package com.clemble.casino.player.service;

import com.clemble.casino.ClembleService;

/**
 * Created by mavarazy on 12/8/14.
 */
public interface PlayerPhoneService extends ClembleService {

    public boolean add(String phone);

    public boolean verify(String code);

}
