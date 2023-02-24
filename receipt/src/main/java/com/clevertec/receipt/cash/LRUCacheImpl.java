package com.clevertec.receipt.cash;

import lombok.Data;

import java.util.*;

public class LRUCacheImpl<T> implements Cache<Integer, T> {
    private final Map<Integer, T> cacheData = new HashMap<>();

    private final List<Integer> order = new LinkedList<>();

    private final int cacheCapacity;

    public LRUCacheImpl(int cacheCapacity) {
        this.cacheCapacity = cacheCapacity;
    }

    @Override
    public void put(Integer key, T value) {

        if (order.size() >= cacheCapacity) {

            Integer keyRemoved = order.remove(order.size() - 1);
            cacheData.remove(keyRemoved);
        }
        order.add(0, key);
        cacheData.put(key, value);
    }

    @Override
    public <T> T get(Integer key) {

        T value = (T) cacheData.get(key);
        if (Objects.nonNull(value)) {

            order.remove(key);
            order.add(0, key);
            return value;
        }
        return null;
    }
}
