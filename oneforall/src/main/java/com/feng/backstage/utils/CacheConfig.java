package com.feng.backstage.utils;

import com.feng.backstage.base.Constants;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public SimpleCacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        List list = new ArrayList();
        list.add(buildAccessTokenCache());
        manager.setCaches(list);
        return manager;
    }

    private GuavaCache buildAccessTokenCache() {
        return new GuavaCache(Constants.ACCESS_TOKEN_CACHE_NAME,
                CacheBuilder.newBuilder()
                        .recordStats()
                        .expireAfterAccess(50, TimeUnit.MINUTES)
                        .build());
    }

}
