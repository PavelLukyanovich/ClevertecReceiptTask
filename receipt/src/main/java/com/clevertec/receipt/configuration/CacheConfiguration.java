package com.clevertec.receipt.configuration;

import com.clevertec.receipt.cash.Cache;
import com.clevertec.receipt.cash.LFUCacheImpl;
import com.clevertec.receipt.cash.LRUCacheImpl;
import com.clevertec.receipt.models.entities.User;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration {

    @Value("${cache.type}")
    private String cacheType;

    @Bean
    public Cache setCacheType() {
        if (cacheType.equals("LFU")) {
            return new LFUCacheImpl<User>();
        } else if (cacheType.equals("LRU")) {
            return new LRUCacheImpl<User>();
        }
        throw new ExecutionException("Type of cache incorrect ");

    }
}
