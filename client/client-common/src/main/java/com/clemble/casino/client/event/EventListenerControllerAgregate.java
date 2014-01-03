package com.clemble.casino.client.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;

public class EventListenerControllerAgregate implements EventListenerController {

    final private Collection<EventListenerController> listenerControllers;

    public EventListenerControllerAgregate(Collection<EventListenerController> listenerController) {
        this.listenerControllers = checkNotNull(listenerController);
    }

    @Override
    public void close() {
        for (EventListenerController listenerController : listenerControllers)
            if (listenerController != null)
                listenerController.close();
    }

}
