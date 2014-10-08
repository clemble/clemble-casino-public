package com.clemble.casino.game.lifecycle.management.unit;

import java.util.ArrayList;
import java.util.List;

public class GameUnitUtils {

    public static List<GameUnit> toList(GameUnit[][] units, GameUnit sel) {
        // Step 1. Preparing GameUnit
        List<GameUnit> resList = new ArrayList<GameUnit>();
        // Step 2. Processing line by line
        for (GameUnit[] arUnit : units)
            for (GameUnit unit : arUnit)
                resList.add(unit);
        // Step 3. Adding
        resList.add(sel);
        // Step 4. Returning result List
        return resList;
    }

}
