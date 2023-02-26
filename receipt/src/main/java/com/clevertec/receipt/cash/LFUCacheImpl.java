package com.clevertec.receipt.cash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LFUCacheImpl<T> implements Cache<Integer, T> {

    private final Map<Integer, Node> cacheStorage = new LinkedHashMap<>();
    @Value("${cache.capacity}")
    private int cacheCapacity;

    @Override
    public T put(Integer key, T value) {

        if (cacheStorage.size() < cacheCapacity) {
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

    @Override
    public T remove(Integer key) {
        return (T) cacheStorage.remove(key);
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
