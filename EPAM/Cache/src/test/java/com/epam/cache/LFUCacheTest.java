package com.epam.cache;

import com.epam.cacheconfiguration.LFUCacheConfiguration;
import org.junit.Before;
import org.junit.Test;

import javax.cache.configuration.MutableConfiguration;

import static org.junit.Assert.*;

public class LFUCacheTest {

    private static final int CACHE_CAPACITY = 10;

    private static final String KEY = "key";
    private static final String VALUE = "value";

    private static final String LAST_FREQUENTLY = "last_frequently";

    private static final String NEW_KEY = "newkey";
    private static final String NEW_VALUE = "newvalue";

    private LFUCacheConfiguration cacheConfiguration;

    private LFUCache<String, String> cache;

    @Before
    public void before() {
        setUpCache(CACHE_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExeptionWhenCreateCacheWithNegativeCapacity() {
        int capacity = -1;

        setUpCache(capacity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExeptionWhenCreateCacheWithZeroCapacity() {
        int capacity = 0;

        setUpCache(capacity);
    }

    @Test
    public void shouldCreateCacheWithCapacity() {
        int capacity = 10;

        setUpCache(capacity);
    }

    @Test
    public void shouldReturnValueWhenGetWithExistingKey() {
        cache.put(KEY, VALUE);

        String actualValue = cache.get(KEY);

        assertEquals(VALUE, actualValue);
    }

    @Test
    public void shouldReturnNullWhenGetWithNotExistingKey() {
        String actualValue = cache.get(KEY);

        assertEquals(null, actualValue);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenGetWithKeyNull() {
        cache.get(null);
    }

    @Test
    public void shouldPutEntryWhenPutWithKeyAndValue() {
        cache.put(KEY, VALUE);
    }

    @Test
    public void shouldRemoveOneEntryWhenPutAndCapacityIsFull() {
        setUpCache(1);

        cache.put(KEY, VALUE);
        cache.put(NEW_KEY, NEW_VALUE);

        assertEquals(NEW_VALUE, cache.get(NEW_KEY));
        assertNull(cache.get(KEY));
    }

    @Test
    public void shouldRemoveLastFrequentlyUsedEntryWhenPutMoreEntryThanCapacity() {
        setUpCache(2);

        cache.put(KEY, VALUE);
        cache.put(LAST_FREQUENTLY, VALUE);

        cache.get(KEY);

        cache.put(NEW_KEY, NEW_VALUE);

        assertEquals(VALUE, cache.get(KEY));
        assertEquals(NEW_VALUE, cache.get(NEW_KEY));
        assertNull(cache.get(LAST_FREQUENTLY));

    }

    @Test
    public void shouldPutEntryWithNewValueInsteadOldValueWhenPutWithSameKeyAndNewValue() {
        cache.put(KEY, VALUE);
        cache.put(KEY, NEW_VALUE);

        assertEquals(NEW_VALUE, cache.get(KEY));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPutWithKeyNull() {
        cache.put(null, VALUE);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenPutWithValueNull() {
        cache.put(KEY, null);
    }

    @Test
    public void shouldRemoveEntryWhenRemoveWithExistingKey() {
        cache.put(KEY, VALUE);

        boolean result = cache.remove(KEY);

        assertTrue(result);
        assertNull(cache.get(KEY));
    }

    @Test
    public void shouldNotRemoveEntryWhenRemoveWithNotExistingKey() {
        boolean result = cache.remove(KEY);

        assertFalse(result);
    }

    @Test
    public void shouldReplaceValueWhenReplaceWithExistingKey() {
        cache.put(KEY, VALUE);

        boolean result = cache.replace(KEY, NEW_VALUE);

        assertTrue(result);
        assertEquals(NEW_VALUE, cache.get(KEY));
    }

    @Test
    public void shouldNotReplaceValueWhenReplaceWithNotExistingKey() {
        boolean result = cache.replace(KEY, NEW_VALUE);

        assertFalse(result);
        assertNull(cache.get(KEY));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenReplaceWithKeyNull() {
        cache.replace(null, VALUE);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenReplaceWithValueNull() {
        cache.replace(KEY, null);
    }

    @Test
    public void shouldClearCacheWhenClear(){
        cache.put(KEY, VALUE);

        cache.clear();

        assertNull(cache.get(KEY));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenGetConfigurationWithUncorrectConfigurationType() {
        cache.getConfiguration(MutableConfiguration.class);
    }

    @Test
    public void shouldReturnConfigurationWhenGetConfigurationWitCorrectConfigurationType() {
        LFUCacheConfiguration configuration = cache.getConfiguration(LFUCacheConfiguration.class);

        assertEquals(configuration, cacheConfiguration);
    }


    private void setUpCache(int capacity) {
        cacheConfiguration = new LFUCacheConfiguration(capacity, String.class, String.class);
        cache = new LFUCache<>(cacheConfiguration);
    }
}