package com.clemble.casino.registration.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.registration.PlayerPasswordResetRequest;

/**
 * Created by mavarazy on 2/2/15.
 */
public interface PlayerPasswordResetService extends ClembleService {

    boolean restore(String email);

    boolean reset(PlayerPasswordResetRequest resetRequest);

}
