package com.clemble.casino.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerAwareUtils {

    private PlayerAwareUtils() {
        throw new IllegalAccessError();
    }

    public static <M extends PlayerAware> M fetch(String player, Collection<? extends M> sourceCollection) {
        // Step 1. Sanity check
        if (player == null)
            return null;
        // Step 2. Going through the elements, looking for associated M
        for (M element : sourceCollection)
            if (player.equals(element.getPlayer()))
                return element;
        // Step 3. Nothing was found return null
        return null;
    }

    public static <M extends PlayerAware> Map<String, M> toMap(Collection<? extends M> sourceCollection) {
        // Step 0. Sanity check
        if (sourceCollection == null || sourceCollection.isEmpty())
            return Collections.emptyMap();
        // Step 1. Converting to Map
        HashMap<String, M> tmpMap = new HashMap<String, M>();
        for (M value : sourceCollection) {
            if (value != null)
                tmpMap.put(value.getPlayer(), value);
        }
        // Step 2. Creating immutable map from tmp map
        return tmpMap;
    }

    public static <M extends PlayerAware> Map<String, M> toImmutableMap(Collection<? extends M> sourceCollection) {
        return Collections.unmodifiableMap(toMap(sourceCollection));
    }

    public static <M extends PlayerAware> List<String> toPlayerList(Collection<? extends M> sourceCollection) {
        List<String> playerList = new ArrayList<String>();
        for (M playerAware : sourceCollection)
            playerList.add(playerAware.getPlayer());
        return playerList;
    }

    public static <M extends PlayerAware> boolean contains(Collection<M> playerAwares, String player) {
        // Step 1. Sanity check
        if(playerAwares == null || player == null)
            return false;
        // Step 2. Checking each player
        for(M playerAware: playerAwares)
            if(playerAware.getPlayer().equals(player))
                return true;
        return false;
    }

}
