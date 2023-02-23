package com.clevertec.receipt.cash;

import com.clevertec.receipt.models.entities.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class LRUCacheImpl {
    // Для хранения данных
    HashMap<Integer, User> data = new HashMap<>();
    // для хранения порядка доступа к кешу
    LinkedList<Integer> order = new LinkedList<>();
    // размер кэша
    int capacity;

    public LRUCacheImpl(int capacity) {
        this.capacity = capacity;
    }

    // метод для добавления новых данных в кеш

    public void put(Integer key, User user) {

        // проверяем не заполнен ли кеш
        if (order.size() >= capacity) {
            // если кеш заполнен удаляем последний элемент из order и затем из data
            Integer keyRemoved = order.removeLast();
            data.remove(keyRemoved);
        }
        // добавляем новые данные в начало order и затем в data
        order.addFirst(key);
        data.put(key, user);

    }

    // метод для получения данных из кеша
    public User get(Integer key) {
        // получаем данные по ключу
        User user = data.get(key);
        // если полученный результат(данные) не null убираем ключ из конца order и добавляем в начало
        if (!Objects.isNull(user)) {
            order.remove(key);
            order.addFirst(key);
        } else {
            // иначе возвращаем null
            user = null;
        }
        return user;


    }
/*    public void display() {

        for (int i = 0; i < order.size(); i++) {
            Integer key = order.get(i);
            System.out.println(key + " => " + data.get(key));
        }
    }

    public static void main(String[] args) {
        LRUCacheImpl cache = new LRUCacheImpl(3);
        User user = new User();

        cache.put(1, user);
        cache.put(2, user);
        cache.put(3, user);
        cache.display();

        cache.get(2);

        cache.display();

        cache.put(4, user);

        cache.display();



    }*/


}
