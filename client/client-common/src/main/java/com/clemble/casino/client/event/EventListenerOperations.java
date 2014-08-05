package com.clemble.casino.client.event;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.clemble.casino.event.Event;
import com.clemble.casino.event.NotificationMapping;
import com.clemble.casino.game.GameSessionAwareEvent;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.PlayerPresenceChangedEvent;

public interface EventListenerOperations extends PlayerAware, Closeable {

    public EventListenerController subscribe(EventListener<Event> listener);

    public EventListenerController subscribe(GameSessionKey sessionKey, EventListener<GameSessionAwareEvent> listener);

    public EventListenerController subscribe(EventSelector selector, EventListener<? extends Event> listener);

    public EventListenerController subscribe(String channel, EventListener<? extends Event> listener);

    public EventListenerController subscribe(String channel, EventSelector selector, EventListener<? extends Event> listener);

    public EventListenerController subscribeToPaymentEvents(EventListener<PaymentEvent> listener);

    public EventListenerController subscribeToPresenceEvents(String player, EventListener<PlayerPresenceChangedEvent> listener);

    public EventListenerController subscribeToPresenceEvents(List<String> players, EventListener<PlayerPresenceChangedEvent> listener);

    /**
     * Event that emulates server event.
     * 
     * @param event Event, that emulates system event
     */
    public void update(Event event);
    
    /**
     * @param events List of events to use for notification
     */
    public void update(Collection<? extends Event> events);

    public boolean isAlive();

    @Override
    public void close();

}
