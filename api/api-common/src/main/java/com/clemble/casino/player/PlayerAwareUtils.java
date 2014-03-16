package com.clemble.casino.player;

import java.util.*;

public class PlayerAwareUtils {

    private PlayerAwareUtils() {
        throw new IllegalAccessError();
    }

    public static <M extends PlayerAware> M filter(String player, Iterable<? extends M> sourceCollection) {
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

    public static <M extends PlayerAware> Map<String, M> toMap(Iterable<? extends M> sourceCollection) {
        // Step 0. Sanity check
        if (sourceCollection == null)
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
    
    public static <M extends PlayerAware> Map<String, Collection<M>> toMultivalueMap(Iterable<? extends M> sourceCollection) {
     // Step 0. Sanity check
        if (sourceCollection == null)
            return Collections.emptyMap();
        // Step 1. Converting to Map
        HashMap<String, Collection<M>> tmpMultivalueMap = new HashMap<String, Collection<M>>();
        for (M value : sourceCollection) {
            if (value != null) {
                if (tmpMultivalueMap.get(value.getPlayer()) == null)
                    tmpMultivalueMap.put(value.getPlayer(), new ArrayList<M>());
                tmpMultivalueMap.get(value.getPlayer()).add(value);
            }
        }
        // Step 2. Creating immutable map from tmp map
        return tmpMultivalueMap;
    }

    public static <M extends PlayerAware> Map<String, M> toImmutableMap(Iterable<? extends M> sourceCollection) {
        return Collections.unmodifiableMap(toMap(sourceCollection));
    }

    public static <M extends PlayerAware> List<String> toPlayerList(Iterable<? extends M> sourceCollection) {
        List<String> playerList = new ArrayList<String>();
        for (M playerAware : sourceCollection)
            playerList.add(playerAware.getPlayer());
        return playerList;
    }

    public static <M extends PlayerAware> Set<String> toPlayerSet(Iterable<? extends M> sourceCollection) {
        Set<String> playerSet = new HashSet<String>();
        for (M playerAware : sourceCollection)
            playerSet.add(playerAware.getPlayer());
        return playerSet;
    }

    public static <M extends PlayerAware> boolean contains(Iterable<M> playerAwares, String player) {
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
