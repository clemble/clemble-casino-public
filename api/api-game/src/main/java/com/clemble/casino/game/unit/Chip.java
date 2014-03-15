package com.clemble.casino.game.unit;

import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 15/03/14.
 */
@JsonTypeName("chip")
public enum Chip implements GameUnit {

    one,
    two,
    three,
    five,
    ten,
    twenty

}
