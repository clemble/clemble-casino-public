package com.clemble.casino.utils;

import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.player.event.PlayerEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mavarazy on 17/03/14.
 */
public class ExpectedEventUtils {

    private static Map<String, Class<? extends PlayerEvent>> actionToEvent = null;
    private static Map<Class<? extends PlayerEvent>, String> eventToAction = null;

    public static String toActionName(Class<? extends PlayerEvent> event) {
        if (eventToAction == null)
            initialize();
        return eventToAction.get(event);
    }

    public static Class<? extends PlayerEvent> fromActionName(String action) {
        if (actionToEvent == null)
            initialize();
        return actionToEvent.get(action);
    }

    public static void initialize(){
        actionToEvent = ObjectMapperUtils.collectSubtypes(PlayerEvent.class);
        eventToAction = new HashMap<Class<? extends PlayerEvent>, String>();
        for(Map.Entry<String, Class<? extends PlayerEvent>> entry: actionToEvent.entrySet())
            eventToAction.put(entry.getValue(), entry.getKey());
    }

}
