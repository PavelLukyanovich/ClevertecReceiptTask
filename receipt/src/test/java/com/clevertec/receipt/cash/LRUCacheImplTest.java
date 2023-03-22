package com.clevertec.receipt.cash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class LRUCacheImplTest {

    @Test
    void put_whenGetData_thenShouldBeEqualCacheElement() {

        LRUCacheImpl lruCache = new LRUCacheImpl<>(3);

        lruCache.put(1, "data1");
        lruCache.put(2, "data2");
        lruCache.put(3, "data3");


        assertEquals("data1", lruCache.get(1));
        assertEquals("data2", lruCache.get(2));
        assertEquals("data3", lruCache.get(3));


    }

    @Test
    void put_whenCacheIsFull_putShouldEvictFirstElementAndPutAnotherOne() {

        LRUCacheImpl lruCache = new LRUCacheImpl(3);

        lruCache.put(1, "data1");
        lruCache.put(2, "data2");
        lruCache.put(3, "data3");
        lruCache.put(4, "data4");

        assertNull(lruCache.get(1));
        assertEquals(lruCache.get(4), "data4");


    }

    @Test
    void get_whenCacheElementHasCalled_shouldReplaceOnFirstPosition() {

        LRUCacheImpl lruCache = new LRUCacheImpl<>(3);


        lruCache.put(1, "data1");
        lruCache.put(2, "data2");

        lruCache.get(2);

        assertEquals(lruCache.getOrder().get(0), 2 );

    }
}