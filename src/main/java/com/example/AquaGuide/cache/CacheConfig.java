package com.example.AquaGuide.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

public class CacheConfig {
    @Bean
    public CacheManager cacheManager(){
        return new ConcurrentMapCacheManager("observations", "regions", "waters");
    }
}
