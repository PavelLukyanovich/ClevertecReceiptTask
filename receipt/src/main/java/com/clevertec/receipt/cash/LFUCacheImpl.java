package com.clevertec.receipt.cash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс реализующий LFU кеш.
 * @param <T>
 * @Version 1.0
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LFUCacheImpl<T> implements Cache<Integer, T> {
    /**
     * Для хранения данных в кеше исползуется LinkedHashMap в качестве ключа
     * для которой выступает значение типа int, а в качестве значени node.
     * @see Node
     */
    private final Map<Integer, Node> cacheStorage = new LinkedHashMap<>();
    /**
     * Размер кеша задается при конфигурации из application.yml файла.
     */
    @Value("${cache.capacity}")
    private int cacheCapacity;

    /**
     * Метод добавления данных в кеш.
     * Сначала проверяется не заполнен ли кеш полностью. Если место есть, то данные добавляются.
     * Если кеш заполнен, то сначала вычисляется ключ элемента который должен быть удален из
     * кеша (частота использования которого наименьшая), затем по полученному ключу удаляется
     * елемент из кеша и помещается новый элемент в кеш.
     * @param key
     * @param value
     * @return T value
     */

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

    /**
     * Метод получения данных из кеша по ключу.
     * Т.к нам необходимо считать количество обрашений к объектам в кеше, при вызове данного метода
     * увеличивает счетчик обращений к элементу кеша. После чего непосредственно достаем сам элемент.
     * @param key
     * @return T value
     */
    @Override
    public T get(Integer key) {

        Node node = cacheStorage.get(key);
        if (Objects.isNull(node)) {
            return null;
        }
        return (T) node.incrementFrequency().getValue();

    }

    /**
     * Метод удаления элемента из кеша.
     * @param key
     * @return T value
     */
    @Override
    public T remove(Integer key) {
        return (T) cacheStorage.remove(key);
    }

    /**
     * Класс описывающий устройство node. Содержит в себе непосредственно данные и счетчик обращения к ним.
     * Так же есть метод увеличения счетчика обращений.
     * @param <T>
     */
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
