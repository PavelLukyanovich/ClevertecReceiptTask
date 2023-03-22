package com.clevertec.receipt.cash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
/**
 * Класс реализующий LRU кеш.
 * @param <T>
 * @Version 1.0
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LRUCacheImpl<T> implements Cache<Integer, T> {
    /**
     * Для хранения данных в кеше исползуется HashMap в качестве ключа
     * для которой выступает значение типа int, а в качестве значени объект типа Т.
     */
    private final Map<Integer, T> cacheData = new HashMap<>();
    /**
     * Позволяет отслеживать порядок элементов в кеше. Тот кто элемент к которому было обращение
     * попадает в начало списка, соответственно тот к кому давно не обращались находится ближе к концу.
     * Таким образом у нас есть возможность отследить элемент к которому обращались наиболее давно по
     * отношению к другим элементам.
     */

    private final List<Integer> order = new LinkedList<>();
    /**
     * Размер кеша задается при конфигурации из application.yml файла.
     */
    @Value("${cache.capacity}")
    private int cacheCapacity;
    /**
     * Метод добавления данных в кеш.
     * Сначала проверяется не заполнен ли кеш полностью. Если кеш заполнен, то сначала вычисляется
     * ключ элемента который должен быть удален из кеша (последний элемент в order), затем по полученному
     * ключу удаляется елемент из кеша. После этого ключ нового элемента помещается в order на первую позицию.
     * Затем новый элемент добавляется в кеш.
     * @param key
     * @param value
     * @return T value
     */

    @Override
    public T put(Integer key, T value) {

        if (order.size() >= cacheCapacity) {

            Integer keyRemoved = order.remove(order.size() - 1);
            cacheData.remove(keyRemoved);
        }
        order.add(0, key);
        return cacheData.put(key, value);
    }
    /**
     * Метод получения данных из кеша по ключу.
     * Получает данные из кеша и переписывает позицию ключа по которому было обращение к данным.
     * Ключ удаляется из текущей позиции в order и помещается в самое начал.
     * @param key
     * @return T value
     */
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
    /**
     * Метод удаления элемента из кеша.
     * @param key
     * @return T value
     */
    @Override
    public T remove(Integer key) {
        order.remove(key);

        return cacheData.remove(key);
    }
}
