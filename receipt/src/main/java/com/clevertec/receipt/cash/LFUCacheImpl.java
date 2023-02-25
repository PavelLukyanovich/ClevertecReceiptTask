package com.clevertec.receipt.cash;

import lombok.Data;
import org.springframework.cache.annotation.Cacheable;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
@Data
public class LFUCacheImpl<T> implements Cache<Integer, T>{

    Map<Integer, T> cacheData;
    Map<Integer, Integer> counts;
    Map<Integer, LinkedHashSet<Integer>> lists;
    int cacheCapacity;
    int min = -1;

    public LFUCacheImpl(int capacity) {

        cacheCapacity = capacity;
        cacheData = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }
    @Override
    public void put(Integer key, T value) {

        if (cacheData.containsKey(key)) {

            cacheData.put(key, value);
            get(key);
            return;
        }
        if (cacheData.size() >= cacheCapacity) {

            int evict = lists.get(min).iterator().next();
            lists.get(min).remove(evict);
            cacheData.remove(evict);
            counts.remove(evict);
        }

        cacheData.put(key, value);
        counts.put(key, 1);
        min = -1;
        lists.get(1).add(key);

    }

    @Override
    @Cacheable(value = "T", key = "#key")
    public <T> T get(Integer key) {
        if (!cacheData.containsKey(key))
            return null;
        int count = counts.get(key);
        counts.put(key, count + 1);
        lists.get(count).remove(key);

        if (count == min && lists.get(count).size() == 0)
            min++;
        if (!lists.containsKey(count + 1))
            lists.put(count + 1, new LinkedHashSet<>());
        lists.get(count + 1).add(key);
        return (T) cacheData.get(key);
    }
    public void display() {
        cacheData

    }

    public static void main(String[] args) {
        LFUCacheImpl cache = new LFUCacheImpl(3);

        cache.put(1, "data1");
        cache.put(2, "data2");
        cache.put(3, "data3");

        cache.display();


    }

}
