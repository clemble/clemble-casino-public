package com.clemble.casino.utils;

import java.util.*;

public class CollectionUtils {

    public static <T> Set<T> immutableSet(T element) {
        Set<T> immutableSet = new HashSet<T>();
        immutableSet.add(element);
        return immutableSet(immutableSet);
    }

    public static <T> Set<T> immutableSet(Collection<? extends T> set) {
        return Collections.unmodifiableSet(set instanceof Set ? (Set<? extends T>) set : new HashSet<T>(set));
    }

    public static <T> Set<T> immutableSet(T ... set) {
        if (set == null)
            return Collections.emptySet();
        return Collections.unmodifiableSet(new HashSet<T>(Arrays.asList(set)));
    }

    public static <T> List<T> immutableList(Collection<? extends T> list) {
        if (list == null)
            return Collections.emptyList();
        return Collections.unmodifiableList(list instanceof List ? (List<? extends T>) list : new ArrayList<T>(list));
    }

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
        for (Map.Entry<K, V> entry : entrySet) {
            if (entry != null)
                resultMap.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(resultMap);
    }

    @SuppressWarnings("unchecked")
    public static <S, T> Collection<T> filter(Class<T> target, Collection<S> sourceCollection) {
        Collection<T> filtered = new ArrayList<T>();
        for (S source : sourceCollection)
            if (source.getClass() == target)
                filtered.add((T) source);
        return filtered;
    }


    public static <S, T> Collection<T> filter(Class<T> target, S ... sourceCollection) {
        Collection<T> filtered = new ArrayList<T>();
        for (S source : sourceCollection)
            if (source.getClass() == target)
                filtered.add((T) source);
        return filtered;
    }

    @SuppressWarnings("unchecked")
    public static <S, T> T find(Class<T> target, Collection<S> sourceCollection) {
        Collection<T> filtered = new ArrayList<T>();
        for (S source : sourceCollection)
            if (source.getClass() == target)
                return (T) source;
        return null;
    }

    public static <S, T> T find(Class<T> target, S ... sourceCollection) {
        Collection<T> filtered = new ArrayList<T>();
        for (S source : sourceCollection)
            if (source.getClass() == target)
                return (T) source;
        return null;
    }

}
