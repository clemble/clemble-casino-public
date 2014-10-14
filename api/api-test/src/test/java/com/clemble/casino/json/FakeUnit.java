package com.clemble.casino.json;

import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 09/03/14.
 */
@JsonTypeName("fakeunit")
public class FakeUnit implements GameUnit {

    @JsonCreator
    public FakeUnit() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FakeUnit fakeUnit = (FakeUnit) o;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
