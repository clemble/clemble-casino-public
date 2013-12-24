package com.clemble.casino.utils;

import java.util.*;

public class CollectionUtils {

    public static <T> Set<T> immutableSet(T element) {
        Set<T> immutableSet = new HashSet<T>();
        immutableSet.add(element);
        return immutableSet(immutableSet);
    }

    public static <T> Set<T> immutableSet(Collection<? extends T> set) {
        return Collections.<T> unmodifiableSet(set instanceof Set ? (Set<? extends T>) set : new HashSet<T>(set));
    }

    public static <T> List<T> immutableList(Collection<? extends T> list) {
        if (list == null)
            return Collections.emptyList();
        return Collections.<T> unmodifiableList(list instanceof List ? (List<? extends T>) list : new ArrayList<T>(list));
    }

    @SafeVarargs
    public static <T> List<T> immutableList(T... list) {
        if (list == null)
            return Collections.emptyList();
        return immutableList(Arrays.asList(list));
    }

    public static <K, V> Map<K, V> immutableMap(Map<K, V> map) {
        return Collections.unmodifiableMap(map);
    }

    public static <K, V> Map<K, V> immutableMap(Collection<Map.Entry<K, V>> entrySet) {
        Map<K, V> resultMap = new HashMap<K, V>();
        for(Map.Entry<K, V> entry: entrySet) {
            if(entry != null)
                resultMap.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(resultMap);
    }

}
