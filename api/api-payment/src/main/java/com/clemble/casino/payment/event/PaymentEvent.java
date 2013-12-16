package com.clemble.casino.payment.event;

import com.clemble.casino.event.ServerEvent;
import com.clemble.casino.payment.AmountAware;
import com.clemble.casino.player.PlayerAware;

public interface PaymentEvent extends ServerEvent, AmountAware, PlayerAware {

}
