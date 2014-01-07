package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

public interface EventConsumer<T extends Event> extends EventListener<T>, EventSelector{

}
