package com.clemble.casino.json;

import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.game.unit.GameUnitUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

/**
 * Created by mavarazy on 09/03/14.
 */
@JsonTypeName("fake")
public class FakeUnit implements GameUnit {

    final private List<GameUnit> children;

    @JsonCreator
    public FakeUnit(@JsonProperty("children") List<GameUnit> children) {
        this.children = children;
    }

    @Override
    public List<? extends GameUnit> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FakeUnit fakeUnit = (FakeUnit) o;

        if (children != null ? !children.equals(fakeUnit.children) : fakeUnit.children != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return children != null ? children.hashCode() : 0;
    }
}
