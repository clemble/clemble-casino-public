package com.clemble.casino.base;

import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.json.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mavarazy on 17/03/14.
 */
public class ExpectedEventUtils {

    private static Map<String, Class<? extends PlayerAwareEvent>> actionToEvent = null;
    private static Map<Class<? extends PlayerAwareEvent>, String> eventToAction = null;

    public static String toActionName(Class<? extends PlayerAwareEvent> event) {
        if (eventToAction == null)
            initialize();
        return eventToAction.get(event);
    }

    public static Class<? extends PlayerAwareEvent> fromActionName(String action) {
        if (actionToEvent == null)
            initialize();
        return actionToEvent.get(action);
    }

    public static void initialize(){
        actionToEvent = ObjectMapperUtils.collectSubtypes(PlayerAwareEvent.class);
        eventToAction = new HashMap<Class<? extends PlayerAwareEvent>, String>();
        for(Map.Entry<String, Class<? extends PlayerAwareEvent>> entry: actionToEvent.entrySet())
            eventToAction.put(entry.getValue(), entry.getKey());
    }

}
