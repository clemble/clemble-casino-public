package com.clemble.casino.registration.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.registration.PlayerPasswordResetRequest;
import com.clemble.casino.registration.PlayerPasswordRestoreRequest;

/**
 * Created by mavarazy on 2/2/15.
 */
public interface PlayerPasswordResetService extends ClembleService {

    boolean restore(PlayerPasswordRestoreRequest restoreRequest);

    boolean reset(PlayerPasswordResetRequest resetRequest);

}
