package com.clemble.casino.payment;

import com.clemble.casino.money.Operation;
import com.clemble.casino.player.PlayerAware;

/**
 * Created by mavarazy on 16/10/14.
 *
 * Created to unite PendingOperation & PaymentOperation
 */
public interface AccountOperation extends AmountAware, PlayerAware{

    Operation getOperation();

}
