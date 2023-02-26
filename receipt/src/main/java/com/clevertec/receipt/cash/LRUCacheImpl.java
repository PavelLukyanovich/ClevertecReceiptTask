package com.clevertec.receipt.cash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LRUCacheImpl<T> implements Cache<Integer, T> {
    private final Map<Integer, T> cacheData = new HashMap<>();

    private final List<Integer> order = new LinkedList<>();
    @Value("${cache.capacity}")
    private int cacheCapacity;

    @Override
    public T put(Integer key, T value) {

        if (order.size() >= cacheCapacity) {

            Integer keyRemoved = order.remove(order.size() - 1);
            cacheData.remove(keyRemoved);
        }
        order.add(0, key);
        return cacheData.put(key, value);
    }

    @Override
    public T get(Integer key) {

        T value = (T) cacheData.get(key);
        if (Objects.nonNull(value)) {

            order.remove(key);
            order.add(0, key);
            return value;
        }
        return null;
    }

    @Override
    public T remove(Integer key) {
        order.remove(key);

        return cacheData.remove(key);
    }
}
