package com.clevertec.receipt.cash;

import java.util.Optional;

public interface Cache<Integer, T> {
    void put(Integer key, T value);
    <T>T get(Integer key);
}
