package com.epam.cacheconfiguration;

import javax.cache.configuration.Configuration;

/**
 * @author Andrii_Tkachuk
 * @since 12/1/2015
 */
public class LFUCacheConfiguration<K, V> implements Configuration<K, V> {

    private int capacity;
    private Class<K> keyType;
    private Class<V> valueType;

    public LFUCacheConfiguration(int capacity, Class<K> keyType, Class<V> valueType) {
        this.capacity = capacity;
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public Class<K> getKeyType() {
        return keyType;
    }

    @Override
    public Class<V> getValueType() {
        return valueType;
    }

    @Override
    public boolean isStoreByValue() {
        return false;
    }

    public int getCapacity() {
        return capacity;
    }
}
