package com.clemble.casino.game.unit;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.utils.CollectionUtils;

public class AbstractGameUnit implements GameUnit {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = 7253854738733731348L;

    final private List<GameUnit> children;

    public AbstractGameUnit() {
        this.children = CollectionUtils.immutableList();
    }

    public AbstractGameUnit(Collection<GameUnit> units) {
        this.children = CollectionUtils.immutableList(units);
    }

    @Override
    public List<GameUnit> getChildren() {
        return children;
    }

}
