package com.clemble.casino.utils;

import com.clemble.casino.event.Event;
import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mavarazy on 17/03/14.
 */
public class ExpectedEventUtils {

    private static Map<String, Class<? extends Event>> actionToEvent = null;
    private static Map<Class<? extends Event>, String> eventToAction = null;

    public static String toActionName(Class<? extends Event> event) {
        if (eventToAction == null)
            initialize();
        return eventToAction.get(event);
    }

    public static <T extends Event & PlayerAware> Class<T> fromActionName(String action) {
        if (actionToEvent == null)
            initialize();
        // TODO this is a workaround
        return (Class<T>) actionToEvent.get(action);
    }

    public static void initialize(){
        actionToEvent = ObjectMapperUtils.collectSubtypes(Event.class);
        eventToAction = new HashMap<Class<? extends Event>, String>();
        for(Map.Entry<String, Class<? extends Event>> entry: actionToEvent.entrySet())
            eventToAction.put(entry.getValue(), entry.getKey());
    }

}
