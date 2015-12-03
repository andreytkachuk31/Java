package com.epam.cache;

import com.epam.cacheconfiguration.LFUCacheConfiguration;

import javax.cache.configuration.Configuration;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author Andrii_Tkachuk
 * @since 11/30/2015
 */
public class LFUCache<K, V> extends AbstractCache<K, V> {

    private Map<K, CacheEntry<V>> cacheMap = new HashMap<>();
    private LFUCacheConfiguration<K, V> cacheConfiguration;

    public LFUCache(LFUCacheConfiguration<K, V> configuration) {
        if (configuration.getCapacity() <= 0)
            throw new IllegalArgumentException();

        this.cacheConfiguration = configuration;
    }

    @Override
    public V get(K key) {
        if (isNull(key))
            throw new NullPointerException();

        synchronized (this) {

            CacheEntry<V> cacheEntry = cacheMap.get(key);
            if (isNull(cacheEntry))
                return null;

            cacheEntry.frequency++;

            return cacheEntry.value;
        }
    }

    @Override
    public void put(K key, V value) {
        if (isNull(key) || isNull(value))
            throw new NullPointerException();

        synchronized (this) {

            if (cacheMap.size() >= cacheConfiguration.getCapacity())
                removeLFU();

            cacheMap.put(key, new CacheEntry<>(value));
        }
    }

    @Override
    public boolean remove(K key) {
        if (isNull(key))
            throw new NullPointerException();

        synchronized (this) {

            CacheEntry cacheEntry = cacheMap.remove(key);

            return nonNull(cacheEntry);
        }

    }

    @Override
    public boolean replace(K key, V value) {
        if (isNull(key) || isNull(value))
            throw new NullPointerException();

        synchronized (this) {

            CacheEntry<V> cacheEntry = cacheMap.get(key);
            if (isNull(cacheEntry))
                return false;

            cacheEntry.value = value;
            return true;
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            cacheMap.clear();
        }
    }

    @Override
    public <C extends Configuration<K, V>> C getConfiguration(Class<C> configurationType) {
        if (configurationType.isAssignableFrom(cacheConfiguration.getClass()))
            return configurationType.cast(cacheConfiguration);

        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "LFUCache[" + cacheMap + "]";
    }

    private void removeLFU() {
        Map.Entry<K, CacheEntry<V>> cacheEntry =
                cacheMap.entrySet()
                        .stream()
                        .min(comparing(e -> e.getValue().frequency))
                        .get();

        cacheMap.remove(cacheEntry.getKey());
    }

    private class CacheEntry<V> {
        V value;
        int frequency;

        public CacheEntry(V value) {
            this(value, 0);
        }

        public CacheEntry(V value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "{value=" + value + ", frequency=" + frequency + "}";
        }
    }
}
