package com.clemble.casino.client.payment;

import com.clemble.casino.client.event.EventSelector;
import com.clemble.casino.event.Event;
import com.clemble.casino.payment.event.PaymentEvent;

/**
 * Created by mavarazy on 13/03/14.
 */
public class PaymentTransactionEventSelector implements EventSelector {

    final private String transactionKey;

    public PaymentTransactionEventSelector(String transactionKey) {
        this.transactionKey = transactionKey;
    }

    @Override
    public boolean filter(Event event) {
        return (event instanceof PaymentEvent)
            && transactionKey.equals(((PaymentEvent) event).getTransactionKey());
    }
}
