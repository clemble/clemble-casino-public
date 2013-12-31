package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

public interface EventListener<T extends Event> {

    public void onEvent(T event);

}
