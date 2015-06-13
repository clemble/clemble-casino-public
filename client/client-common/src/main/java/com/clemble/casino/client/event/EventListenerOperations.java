package com.clemble.casino.client.event;

import java.io.Closeable;
import java.util.Collection;

import com.clemble.casino.event.Event;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.player.PlayerAware;

public interface EventListenerOperations extends PlayerAware, Closeable {

    EventListenerController subscribe(EventListener<Event> listener);

    EventListenerController subscribe(EventSelector selector, EventListener<? extends Event> listener);

    EventListenerController subscribe(String channel, EventListener<? extends Event> listener);

    EventListenerController subscribe(String channel, EventSelector selector, EventListener<? extends Event> listener);

    EventListenerController subscribeToPaymentEvents(EventListener<PaymentEvent> listener);

    /**
     * Event that emulates server event.
     * 
     * @param event Event, that emulates system event
     */
    void update(Event event);
    
    /**
     * @param events List of events to use for notification
     */
    void update(Collection<? extends Event> events);

    boolean isAlive();

    @Override
    void close();

}
