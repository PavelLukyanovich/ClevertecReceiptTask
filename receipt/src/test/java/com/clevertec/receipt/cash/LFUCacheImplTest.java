package com.clevertec.receipt.cash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class LFUCacheImplTest {

    @Test
    void put_whenCacheIsFull_shouldPutInCacheNewDataAndRemoveOldThenLessFrequency() {
        LFUCacheImpl cache = new LFUCacheImpl<>(3);

        cache.put(1, "data1");
        cache.put(2, "data2");
        cache.put(3, "data3");

        cache.get(1);
        cache.get(1);
        cache.get(2);

        cache.put(4, "data4");


        assertFalse(cache.getCacheStorage().containsValue("data3"));

    }

    @Test
    void put_whenCacheIsFull_shouldPutInCacheNewDataAndSetFrequency1() throws NoSuchFieldException, IllegalAccessException {
        LFUCacheImpl cache = new LFUCacheImpl<>(3);

        cache.put(1, "data1");
        cache.put(2, "data2");
        cache.put(3, "data3");

        cache.get(1);
        cache.get(1);
        cache.get(2);

        cache.put(4, "data4");

        Field field = cache.getCacheStorage().get(4).getClass().getDeclaredField("frequency");
        long value = (long) field.get(cache.getCacheStorage().get(4));

        Assertions.assertEquals(1, value);
    }
}