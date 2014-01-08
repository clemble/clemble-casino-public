package com.clemble.casino.payment.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.payment.AmountAware;
import com.clemble.casino.payment.PaymentTransactionAware;
import com.clemble.casino.player.PlayerAware;

public interface PaymentEvent extends Event, AmountAware, PlayerAware, PaymentTransactionAware {

}
