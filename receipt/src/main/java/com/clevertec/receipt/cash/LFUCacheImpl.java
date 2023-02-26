package com.clevertec.receipt.cash;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class LFUCacheImpl<T> implements Cache<Integer, T> {

    private final LinkedHashMap<Integer, Node> cacheStorage;
    private final int capacity;

    public LFUCacheImpl(int capacity) {

        this.capacity = capacity;
        cacheStorage = new LinkedHashMap<>(capacity, 1);
    }


    @Override
    public  T put(Integer key, T value) {

        if (cacheStorage.size() < capacity) {
            cacheStorage.put(key, new Node(value));
        }

        long minFrequency = Long.MAX_VALUE;
        Integer keyToRemove = null;

        for (Map.Entry<Integer, Node> entry : cacheStorage.entrySet()) {
            if (Objects.equals(entry.getKey(), key)) {

            }

            if (minFrequency >= entry.getValue().getFrequency()) {
                minFrequency = entry.getValue().getFrequency();
                keyToRemove = entry.getKey();
            }
        }
        cacheStorage.remove(keyToRemove);
       return (T) cacheStorage.put(key, new Node(value));
    }

    @Override
    public T get(Integer key) {

        Node node = cacheStorage.get(key);
        if (Objects.isNull(node)) {
            return null;
        }
        return (T) node.incrementFrequency().getValue();

    }

    @Data
    private class Node<T> {

        private final T value;
        public long frequency;

        public Node(T value) {
            this.value = value;
            this.frequency = 1;
        }

        public Node incrementFrequency() {
            ++frequency;
            return this;
        }
    }
}
