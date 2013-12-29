package com.clemble.casino.client.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.utils.CollectionUtils;

public class GameUnitWrapper<T extends GameUnit> implements GameUnit, Serializable {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = -4101510061379823793L;

    private volatile GameUnit currentUnit;
    private volatile List<GameUnitWrapper<GameUnit>> children;

    public GameUnitWrapper() {
    }

    public GameUnitWrapper(T unit) {
        this.currentUnit = unit;
        List<GameUnitWrapper<GameUnit>> tmpChildren = new ArrayList<GameUnitWrapper<GameUnit>>();
        for (GameUnit child : currentUnit.getChildren()) {
            tmpChildren.add(new GameUnitWrapper<GameUnit>(child));
        }
        this.children = CollectionUtils.immutableList(tmpChildren);
    }

    public GameUnit get() {
        return currentUnit;
    }

    public Collection<GameUnitEvent> set(GameUnit unit) {
        Collection<GameUnitEvent> allEvents = new ArrayList<GameUnitEvent>();
        if (!currentUnit.equals(unit)) {
            // Step 1. Saving old unit for notification
            GameUnitEvent changedEvent = new GameUnitChangedEvent(currentUnit, unit);
            this.currentUnit = unit;
            // Step 2. Updating children
            allEvents.add(changedEvent);
            List<? extends GameUnit> currentChildren = currentUnit.getChildren();
            for (int i = 0; i < children.size(); i++)
                allEvents.addAll(children.get(i).set(currentChildren.get(i)));
        }
        return allEvents;
    }

    @Override
    public List<GameUnitWrapper<GameUnit>> getChildren() {
        return children;
    }

}
