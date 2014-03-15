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
