package com.clemble.casino.client.event;

import java.io.Closeable;
import java.util.Collection;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.PlayerAware;

public interface EventListenerOperations extends PlayerAware, Closeable {

    public EventListenerController subscribe(EventListener listener);

    public EventListenerController subscribe(GameSessionKey sessionKey, EventListener listener);

    public EventListenerController subscribe(EventSelector selector, EventListener listener);

    public EventListenerController subscribe(String channel, EventListener listener);

    public EventListenerController subscribe(String channel, EventSelector selector, EventListener listener);

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
