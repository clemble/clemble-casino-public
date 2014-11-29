package com.clemble.casino.payment.notification;

import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.AmountAware;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 11/29/14.
 */
public interface PaymentNotification extends PlayerNotification, AmountAware {
}
