package com.clevertec.receipt.cash;

import java.util.Optional;

public interface Cache<Integer, T> {
     T put(Integer key, T value);
    T get(Integer key);
    T remove(Integer key);
}
