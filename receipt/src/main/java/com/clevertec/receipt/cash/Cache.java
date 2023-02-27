package com.clevertec.receipt.cash;

public interface Cache<Integer, T> {
    T put(Integer key, T value);

    T get(Integer key);

    T remove(Integer key);
}
