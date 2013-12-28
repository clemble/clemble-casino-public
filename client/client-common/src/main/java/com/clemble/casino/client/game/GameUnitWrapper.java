package com.clemble.casino.client.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.GameUnitChangedEvent;
import com.clemble.casino.client.event.GameUnitEvent;
import com.clemble.casino.game.unit.GameUnit;

public class GameUnitWrapper<T extends GameUnit> implements Serializable {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = -4101510061379823793L;

    private Collection<EventListener> listeners = new ArrayList<EventListener>();
    private volatile T currentUnit;

    public GameUnitWrapper(T unit) {
        this.currentUnit = unit;
    }

    public void subscribe(EventListener listener) {
        this.listeners.add(listener);
    }

    public T get() {
        return currentUnit;
    }

    public void set(T unit) {
        if (!currentUnit.equals(unit)) {
            // Step 1. Saving old unit for notification
            GameUnitEvent changedEvent = new GameUnitChangedEvent(currentUnit, unit);
            this.currentUnit = unit;
            // Step 2. Processing Game Change event
            for (EventListener listener : listeners) {
                listener.onEvent(changedEvent);
            }
        }
    }

}
