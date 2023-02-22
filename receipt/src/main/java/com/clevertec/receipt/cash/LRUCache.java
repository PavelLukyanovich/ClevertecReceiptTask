package com.clevertec.receipt.cash;

import com.clevertec.receipt.models.entities.User;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class LRUCache<K,V> implements Cache<K,V> {

    private int size;
    private Map<K, LinkedListNode<User<K,V>>> linkedListNodeMap;
    private DoublyLinkedList<User<K,V>> doublyLinkedList;

    public LRUCache(int size) {
        this.size = size;
        this.linkedListNodeMap = new HashMap<>(maxSize);
        this.doublyLinkedList = new DoublyLinkedList<User<K, V>>();
    }

    @Override
    public boolean set(Object key, Object value) {
        return false;
    }

    @Override
    public Optional get(Object key) {
        return Optional.empty();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }
}
