package com.clemble.casino.registration.service;

import com.clemble.casino.PlayerService;
import com.clemble.casino.registration.PlayerPasswordChangeRequest;
import com.clemble.casino.registration.PlayerPasswordResetRequest;
import com.clemble.casino.registration.PlayerPasswordRestoreRequest;

/**
 * Created by mavarazy on 2/2/15.
 */
public interface PlayerPasswordService extends PlayerService {

    boolean restore(PlayerPasswordRestoreRequest restoreRequest);

    boolean reset(PlayerPasswordResetRequest resetRequest);

    boolean change(PlayerPasswordChangeRequest changeRequest);

}
