package com.epam.cachemanager;

import com.epam.cacheconfiguration.LFUCacheConfiguration;
import org.junit.Before;
import org.junit.Test;

import javax.cache.Cache;
import javax.cache.CacheException;

import static org.junit.Assert.*;

public class LFUCacheManagerTest {

    public static final int CAPACITY = 3;
    public static final String CACHE_NAME = "cache_name";

    private LFUCacheConfiguration<String, String> cacheConfiguration;

    private LFUCacheManager cacheManager = new LFUCacheManager();

    @Before
    public void before() {
        setUpCacheConfiguration();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEWhenCreateCacheWithCacheNameNull() {
        cacheManager.createCache(null,  cacheConfiguration);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEWhenCreateCacheWithConfigurationNull() {
        cacheManager.createCache(CACHE_NAME,  null);
    }

    @Test(expected = CacheException.class)
    public void shouldThrowCacheExceptionWhenCreateCacheAndCacheNameAlreadyExist() {
        cacheManager.createCache(CACHE_NAME,  cacheConfiguration);
        cacheManager.createCache(CACHE_NAME, cacheConfiguration);
    }

    @Test
    public void shouldCreateCacheWhenCreateCache() {
        Cache<String, String> cache = cacheManager.createCache(CACHE_NAME, cacheConfiguration);

        assertNotNull(cache);
        assertEquals(cacheConfiguration, cache.getConfiguration(LFUCacheConfiguration.class));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNPEWhenDestroyCacheWithCacheNameNull() {
        cacheManager.destroyCache(null);
    }

    @Test
    public void shouldDestroyCacheWhenDestroyCache() {
        cacheManager.createCache(CACHE_NAME, cacheConfiguration);

        cacheManager.destroyCache(CACHE_NAME);

        cacheManager.createCache(CACHE_NAME, cacheConfiguration);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenGetCacheWithUnExistingCacheName() {
        cacheManager.getCache(CACHE_NAME, String.class, String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenGetCacheWithDifferentKeyType() {
        cacheManager.getCache(CACHE_NAME, Double.class, String.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenGetCacheWithDifferentValueType() {
        cacheManager.getCache(CACHE_NAME, String.class, Double.class);
    }

    @Test
    public void shouldGetCacheWhenGetCache() {
        cacheManager.createCache(CACHE_NAME, cacheConfiguration);

        Cache<String, String> cache = cacheManager.getCache(CACHE_NAME, String.class, String.class);

        assertNotNull(cache);
        assertEquals(cacheConfiguration, cache.getConfiguration(LFUCacheConfiguration.class));
    }

    private void setUpCacheConfiguration() {
        cacheConfiguration = new LFUCacheConfiguration<>(CAPACITY, String.class, String.class);
    }
}