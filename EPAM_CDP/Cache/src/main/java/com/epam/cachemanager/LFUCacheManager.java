package com.epam.cachemanager;

import com.epam.cache.LFUCache;
import com.epam.cacheconfiguration.LFUCacheConfiguration;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.configuration.Configuration;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author Andrii_Tkachuk
 * @since 12/1/2015
 */
public class LFUCacheManager extends AbstractCacheManager {

    private Map<String, Cache> caches = new HashMap<>();

    @Override
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C configuration) throws IllegalArgumentException {
        if (isNull(cacheName) || isNull(configuration))
            throw new NullPointerException();

        synchronized (this) {
            if (configuration instanceof LFUCacheConfiguration && !caches.containsKey(cacheName)) {
                LFUCache<K, V> cache = new LFUCache<>((LFUCacheConfiguration<K, V>) configuration);
                caches.put(cacheName, cache);

                return cache;
            }
        }

        throw new CacheException();
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType, Class<V> valueType) {
        synchronized (this) {
            Cache cache = caches.get(cacheName);

            if (nonNull(cache) && cache instanceof LFUCache) {
                LFUCache<K, V> lfuCache = (LFUCache<K, V>) cache;
                LFUCacheConfiguration<K, V> cacheConfiguration = lfuCache.getConfiguration(LFUCacheConfiguration.class);

                if (isNotAssignableFrom(cacheConfiguration.getKeyType(), keyType) || isNotAssignableFrom(cacheConfiguration.getValueType(), valueType))
                    throw new IllegalArgumentException();

                return lfuCache;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) {
        synchronized (this) {
            return getCache(cacheName, (Class<K>)Object.class, (Class<V>)Object.class);
        }
    }

    @Override
    public void destroyCache(String cacheName) {
        if (isNull(cacheName))
            throw new NullPointerException();

        synchronized (this) {
            caches.remove(cacheName);
        }
    }
    private <K> boolean isNotAssignableFrom(Class<K> keyType, Class<?> actualKeyType) {
        return !keyType.isAssignableFrom(actualKeyType);
    }
}
