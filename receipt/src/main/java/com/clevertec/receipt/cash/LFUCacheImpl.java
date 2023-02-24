package com.clevertec.receipt.cash;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCacheImpl<T> implements Cache<Integer, T>{

    Map<Integer, T> values;//cache K and V
    Map<Integer, Integer> counts;//K and counters
    Map<Integer, LinkedHashSet<Integer>> lists;//Counter and item list
    int cacheCapacity;
    int min = -1;

    public LFUCacheImpl(int capacity) {
        cacheCapacity = capacity;
        values = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }
    @Override
    public void put(Integer key, T value) {
        if (cacheCapacity <= 0)
            return;
        // If key does exist, we are returning from here
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }
        if (values.size() >= cacheCapacity) {
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            values.remove(evit);
            counts.remove(evit);
        }
        // If the key is new, insert the value and current min should be 1 of course
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);

    }

    @Override
    public <T> T get(Integer key) {
        if (!values.containsKey(key))
            return null;
        // Get the count from counts map
        int count = counts.get(key);
        // increase the counter
        counts.put(key, count + 1);
        // remove the element from the counter to linkedhashset
        lists.get(count).remove(key);

        // when current min does not have any data, next one would be the min
        if (count == min && lists.get(count).size() == 0)
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return (T) values.get(key);
    }

}
