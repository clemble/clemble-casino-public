package com.clemble.casino.client.payment;

import com.clemble.casino.client.event.EventSelector;
import com.clemble.casino.event.Event;
import com.clemble.casino.payment.event.PaymentEvent;

public class PaymentEventSelector implements EventSelector {

    @Override
    public boolean filter(Event event) {
        return event instanceof PaymentEvent;
    }

}
